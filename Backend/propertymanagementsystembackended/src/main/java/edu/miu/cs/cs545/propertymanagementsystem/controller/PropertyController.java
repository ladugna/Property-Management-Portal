package edu.miu.cs.cs545.propertymanagementsystem.controller;


import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
import edu.miu.cs.cs545.propertymanagementsystem.service.OfferService;
import edu.miu.cs.cs545.propertymanagementsystem.service.PropertyService;
import edu.miu.cs.cs545.propertymanagementsystem.service.impl.OfferServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<PropertyResponse> getAllProperties(Pageable pageable) {
        return propertyService.findAll(pageable);
    }

    @PostMapping("/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(@RequestBody @Valid PropertyRequest propertyRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(Roles.OWNER.name()))) {
            throw new AccessDeniedException("You are not authorized to access this resource.");
        }
        propertyService.addNewProperty(propertyRequest);
    }

    @GetMapping("/{id}")
    public PropertyResponse getPropertyById(@PathVariable Long id) {
        return propertyService.findPropertyBYId(id);
    }

    @PutMapping("/owner/{id}")
    public PropertyResponse updateProp(@RequestBody PropertyRequest propertyRequest, @PathVariable Long id) {
        return propertyService.updatePropertyById(id, propertyRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/owner/{id}")
    public void deleteProp(@PathVariable Long id) {
        propertyService.deletePropertyById(id);
    }

    @PutMapping("/owner/{id}/pend")
    public Property UpdateToPending(@PathVariable Long id) {
        return propertyService.updateToPending(id);
    }

    @PutMapping("/owner/{id}/availabe")
    public void UpdateToAvailable(@PathVariable Long id) {
        propertyService.updateToAvailable(id);
    }

    @PutMapping("/owner/{id}/contigent")
    public void UpdateToContingent(@PathVariable Long id) {
        propertyService.updateToContingent(id);
    }

    @GetMapping("/city/{city}")
    public List<Property> getPropertyByCity(@PathVariable String city) {
        return propertyService.propertiesByAddress(city);
    }

    @GetMapping("/owner/{id}")
    List<Property> findPropertiesByUsers(@PathVariable Long id){
        return  propertyService.propertiesByUserId(id);
    }


    @PutMapping("/owner/{id}/cancelContingency")
    public void cancelContingency(@PathVariable Long id) {
        propertyService.cancelContingency(id);
    }

    @GetMapping("/active-offer")//all active offer with status pending
    public ResponseEntity<List<Offer>> getAllActiveOffers() {
        List<Offer> activeOffers = offerService.findAllActiveOffers();
        return new ResponseEntity<>(activeOffers, HttpStatus.OK);
    }


    @GetMapping("/owner/{ownerId}/active-offers")
    public List<Offer> getActiveOffersByOwnerId(@PathVariable Long ownerId){
        return  offerService.findActiveOffersByOwnerId(ownerId);
    }
    @GetMapping("/owner/{ownerId}/active-offers/properties")
    public List<Property> getActiveOfferProperties(@PathVariable Long ownerId){
        return offerService.findActiveOffersPropertiesForOwner(ownerId);

    }
    @GetMapping("/{ownerId}/active-offers/customers")
    public List<User> getActiveOfferCustomers(@PathVariable Long ownerId){
        return  offerService.findActiveOfferPropertiesCustomer(ownerId);
    }


    @GetMapping("/owner/{propertyId}")

 public List<Offer> getOffersByPropertyId(@PathVariable Long propertyId){
        return offerService.findOffersByPropertyId(propertyId);
    }
    @PostMapping("/owner/offer/{offerId}/accept")

    public ResponseEntity<?> acceptOffer(@PathVariable Long offerId){
        try {
            Offer acceptedOffer = offerService.acceptOffer(offerId);
            acceptedOffer.setOfferStatus(OfferStatus.ACCEPTED);
            Property property=acceptedOffer.getProperty();

            property.setPropertyStatus(PropertyStatus.PENDING);

            propertyService.updatePropertyById( property.getProperty_id(),modelMapper.map(property, PropertyRequest.class));
            return  new ResponseEntity<>("Offer accepted and property status changed to 'contingent'", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/owner/offer/{offerId}/reject")

    public ResponseEntity<Object> rejectOffer(@PathVariable Long offerId) {
        try {
            offerService.rejectOffer(offerId);
            return new ResponseEntity<>("Offer rejected", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
        }
    }

}