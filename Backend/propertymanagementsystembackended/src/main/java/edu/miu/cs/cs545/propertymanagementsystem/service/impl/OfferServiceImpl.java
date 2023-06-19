package edu.miu.cs.cs545.propertymanagementsystem.service.impl;

import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
import edu.miu.cs.cs545.propertymanagementsystem.repository.OfferRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl {
    private final OfferRepository offerRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, PropertyRepository propertyRepository, UserRepository userRepository){
        this.offerRepository=offerRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }
    public List<Offer> findByCustomerId(Long customerId) {
        return offerRepository.findByUserId(customerId);
    }
    public List<Offer> findActiveOffersByCustomerId(Long customerId){
        User customer= userRepository.findById(Math.toIntExact(customerId)).get();
      if (customer.getRole().equals(Roles.CUSTOMER)) {
          return offerRepository.findActiveOffersByCustomerId(customerId);
      }
        System.out.println("you don't have CUSTOMER access");
      return null;

    }

    public List<Offer> findActiveOffersByOwnerId(Long OwnerId){
        User customer= userRepository.findById(Math.toIntExact(OwnerId)).get();
        if (customer.getRole().equals(Roles.OWNER)) {
            return offerRepository.findActiveOffersByCustomerId(OwnerId);
        }
        System.out.println("You don't have OWNER access");
        return null;
    }


    public List<Offer> findAllActiveOffers() {
        return offerRepository.findByOfferStatus(OfferStatus.PENDING);
    }


    public List<Property> findActiveOffersProperties(Long customerId){
     List<Offer> offers=findActiveOffersByCustomerId(customerId);
        ArrayList<Property>properties = new ArrayList<>();
        for (Offer offer : offers) {
            properties.add(offer.getProperty());
        }
     return properties;
    }

    public List<Property> findActiveOffersPropertiesForOwner(Long ownerId){
        // return properties associated with specific offer
        List<Offer>offers= findActiveOffersByOwnerId(ownerId);
        ArrayList<Property>properties = new ArrayList<>();
        for (Offer offer : offers) {
            properties.add(offer.getProperty());

        }
        return properties;
    }

    public List<User> findActiveOfferPropertiesCustomer(Long ownerId){
        List<Offer> offers =findActiveOffersByOwnerId(ownerId);
        ArrayList<User> users =new ArrayList<>();
        for (Offer offer:offers){
            users.add(offer.getUser());
        }
        return users;
    }

    public  List<Offer> findOffersByPropertyId(Long propertyId){
        return offerRepository.findOfferByPropertyId(propertyId);
    }


    public void rejectOffer(Long offerId) throws ChangeSetPersister.NotFoundException {
        Offer offer = offerRepository.findById(offerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        offer.setOfferStatus(OfferStatus.REJECTED);
        offer.getProperty().setPropertyStatus(PropertyStatus.AVAILABLE);
        offerRepository.save(offer);
    }
    public Offer acceptOffer(Long offerId) throws ChangeSetPersister.NotFoundException {
        Offer offer =offerRepository.findById(offerId).orElseThrow((ChangeSetPersister.NotFoundException::new));
        offer.setOfferStatus(OfferStatus.ACCEPTED);
        offer.getProperty().setPropertyStatus(PropertyStatus.CONTINGENT);
        offerRepository.save(offer);
        return offer;
    }


    public boolean canCancelOffer(Long offerId){
        Optional<Offer> offerOptional= offerRepository.findById(offerId);
        if(offerOptional.isPresent()){
            Offer offer=offerOptional.get();
            Property property= propertyRepository.findById(offer.getProperty().getProperty_id()).orElse(null);
            if(property!=null){
                return !offer.getProperty().getPropertyStatus().equals(PropertyStatus.CONTINGENT);
            }
        }
        return  false;
    }
    public void cancelOffer(Long offerId){
        Optional<Offer> offerOptional =offerRepository.findById(offerId);

        if (offerOptional.isPresent()){
            Offer offer= offerOptional.get();
            Property property=propertyRepository.findById(offer.getProperty().getProperty_id()).orElse(null);

            if(property!=null){
                property.setPropertyStatus(PropertyStatus.AVAILABLE);
                propertyRepository.save(property);
            }
        }
    }


}
