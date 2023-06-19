package edu.miu.cs.cs545.propertymanagementsystem.controller;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.OfferResponse;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.UserResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.FavoriteList;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import edu.miu.cs.cs545.propertymanagementsystem.service.OfferService;
import edu.miu.cs.cs545.propertymanagementsystem.service.PropertyService;
import edu.miu.cs.cs545.propertymanagementsystem.service.impl.CustomerService;
import edu.miu.cs.cs545.propertymanagementsystem.service.impl.FavoriteListService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final FavoriteListService favoriteListService;
    private final OfferService offerService;
    private  final PropertyService propertyService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/")
    public List<UserResponse> getAllCustomer(){
        return customerService.getAllCustomers();
    }


    @GetMapping("/offer-history/{customerId}")
    public List<Offer> getOfferHistoryByCustomerId(@PathVariable Long customerId) {
        return offerService.findByCustomerId(customerId);
    }


    @GetMapping("/{customerId}/active-offers")
    public List<Offer> getActiveOffersByCustomerId(@PathVariable Long customerId){
        return  offerService.findActiveOffersByOwnerId(customerId);
    }

    @GetMapping("/{customerId}/active-offers/properties")
    public List<Property> getActiveOfferProperties(@PathVariable Long customerId){
        return offerService.findActiveOffersProperties(customerId);
    }


    @PostMapping("/offers/{offerId}/cancel")
    public ResponseEntity<String> cancelOffer(@PathVariable Long offerId){
        if(offerService.canCancelOffer(offerId)){
            offerService.cancelOffer(offerId);
            return ResponseEntity.ok("Offer canceled successfully");
        }
        else{
            return new ResponseEntity<>("Cannot cancel offer after contingency.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/place-offer")
    public ResponseEntity<?> placeOffer(
            @RequestParam String propertyId,
            @RequestParam Long userId,
            @RequestParam Double amount) {
        OfferResponse offer = customerService.placeOffer(Long.valueOf(propertyId), userId, amount);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }
    @PostMapping("/add-favorites/{customerId}")
    public ResponseEntity<Object> addSavedProperty(@PathVariable Long customerId,
                                                   @RequestParam Long propertyId,
                                                   @RequestParam String name){
        favoriteListService.addToFavoriteList(customerId,propertyId,name);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("get-favorites/{customerId}")
    public List<FavoriteList> getSavedProperties(@PathVariable Long customerId){
        return favoriteListService.getFavoriteList(customerId);
    }

    @DeleteMapping("/saved-properties/{savedPropertyId}")
    public ResponseEntity<Object> removeSavedProperty(@PathVariable Long savedPropertyId){
        favoriteListService.removeFavoriteList(savedPropertyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/offer/{offerId}/accept")

    public ResponseEntity<Object> acceptOffer(@PathVariable Long offerId){
        try {
            Offer acceptedOffer = offerService.acceptOffer(offerId);
            acceptedOffer.setOfferStatus(OfferStatus.FINISHED);
            Property property=acceptedOffer.getProperty();
            property.setPropertyStatus(PropertyStatus.CONTINGENT);

            propertyService.updatePropertyById(property.getProperty_id(),modelMapper.map(property, PropertyRequest.class));
            return  new ResponseEntity<>("Offer accepted and property status changed to 'contingent'", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
        }

    }
}
