package edu.miu.cs.cs545.propertymanagementsystem.service.impl;


import edu.miu.cs.cs545.propertymanagementsystem.model.FavoriteList;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.repository.FavoriteRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service

public class FavoriteListService {
    private final FavoriteRepository favoriteRepository;
    private final PropertyRepository propertyRepository;
    private  final UserRepository userRepository;
    public FavoriteListService( FavoriteRepository favoriteRepository, PropertyRepository propertyRepository,
                               UserRepository userRepository){
       this.favoriteRepository=favoriteRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }

    public void addToFavoriteList(Long customerId, Long propertyId,String name){
        Optional<FavoriteList> savedListOptional = favoriteRepository.findByCustomerIdAndPropertiesId(customerId,propertyId);

        Property property = propertyRepository.findById(propertyId).get();
        User user= userRepository.findById(Math.toIntExact(customerId)).get();

        if(savedListOptional.isEmpty()){
            FavoriteList favoriteList =new FavoriteList();
            favoriteList.setName(name);
            favoriteList.setProperties(Arrays.asList(property));
            favoriteList.setCustomer(user);
            favoriteRepository.save(favoriteList);
        }
        else {
            property.setFavorites(savedListOptional.get());
            propertyRepository.save(property);
        }
    }

    public List<FavoriteList> getFavoriteList(Long customerId){
        return favoriteRepository.findByCustomerId(customerId);
    }
    public void removeFavoriteList(Long savedPropertyId){
        favoriteRepository.deleteById(savedPropertyId);
    }

}
