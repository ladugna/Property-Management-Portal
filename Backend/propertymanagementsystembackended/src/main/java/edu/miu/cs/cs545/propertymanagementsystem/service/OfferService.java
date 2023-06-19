package edu.miu.cs.cs545.propertymanagementsystem.service;

import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface OfferService {
    public List<Offer> findByCustomerId(Long customerId);
    public List<Offer> findActiveOffersByOwnerId(Long OwnerId);
    public List<Offer> findAllActiveOffers();
    public List<Property> findActiveOffersProperties(Long customerId);
    public List<Property> findActiveOffersPropertiesForOwner(Long ownerId);
    public List<User> findActiveOfferPropertiesCustomer(Long ownerId);
    public  List<Offer> findOffersByPropertyId(Long propertyId);
    public void rejectOffer(Long offerId) throws ChangeSetPersister.NotFoundException;
    public Offer acceptOffer(Long offerId) throws ChangeSetPersister.NotFoundException;

    public boolean canCancelOffer(Long offerId);
    public void cancelOffer(Long offerId);


}
