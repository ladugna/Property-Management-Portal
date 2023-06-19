package edu.miu.cs.cs545.propertymanagementsystem.service.impl;


import edu.miu.cs.cs545.propertymanagementsystem.dto.response.OfferResponse;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.UserResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import edu.miu.cs.cs545.propertymanagementsystem.repository.FavoriteRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.OfferRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerService {
@Autowired
    private OfferRepository offerRepository;
@Autowired
    private FavoriteRepository favoriteRepository;
@Autowired
    private PropertyRepository propertyRepository;
@Autowired
    private UserRepository userRepository;
@Autowired
private ModelMapper modelMapper;



    public List<UserResponse> getAllCustomers() {
        return userRepository.findAllCustomers().stream().map(e->
                modelMapper.map(userRepository.findAllCustomers(), UserResponse.class)).collect(Collectors.toList());
    }


 /*
 *Place offer, the property status will be changed to 'pending'
 *  if the offer gets accepted:
When a customer places an offer, update the
*  property status to 'pending' if the offer is accepted.
*  You can implement this logic in the CustomerService.
 * */

    public OfferResponse placeOffer(Long propertyId, Long userId, Double amount) {
        Property property = propertyRepository.findById(propertyId).orElse(null);

        if (property == null) {
            return null;
        }

        User user = userRepository.findById(Math.toIntExact(userId)).orElse(null);
        if (user == null) {
            return null;
        }

        Offer offer = new Offer();
        offer.setProperty(property);
        offer.setUser(user);
       // offer.setAmount(amount);
        offer.setOfferStatus(OfferStatus.PENDING);

        Offer savedOffer = offerRepository.save(offer);

        if (savedOffer != null) {
            property.setPropertyStatus(PropertyStatus.PENDING);
            propertyRepository.save(property);
        }

        String ownerEmail = savedOffer.getProperty().getUser().getEmail();
        //String ownerEmail1 ="getaunayaleneh@gmail.com";
        String subject = "New offer on your property";
        String message = "You have received a new offer of " + amount + " on your property at " +
                savedOffer.getProperty().getAddress().getCity();
        //emailService.sendEmail(ownerEmail, subject, message);

        return modelMapper.map(savedOffer, OfferResponse.class);
    }
}


