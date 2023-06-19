package edu.miu.cs.cs545.propertymanagementsystem.service.impl;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.UserRepository;
import edu.miu.cs.cs545.propertymanagementsystem.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
//    @Autowired
//    PropertySearchDao propertySearchDao;

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    UserRepository userRepository;

@Override
public Page<PropertyResponse> findAll(Pageable pageable) {
    var propertyPage = propertyRepository.findAll(pageable);
    var propertyResponseList = propertyPage.stream()
            .map(e -> modelmapper.map(e, PropertyResponse.class))
            .collect(Collectors.toList());
    return new PageImpl<>(propertyResponseList, pageable, propertyPage.getTotalElements());

//            return propertyRepository.findAll(pageable).map(e-> modelmapper.map(e,
//                PropertyResponse.class));
}

    @Override
    public PropertyResponse findPropertyBYId(long id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            return modelmapper.map(property, PropertyResponse.class);
        }
        return null;
    }


    @Override
    public void addNewProperty(PropertyRequest propertyRequest) {
           propertyRequest.setPropertyStatus(PropertyStatus.AVAILABLE);
           propertyRepository.save(modelmapper.map(propertyRequest,Property.class));
    }

    @Override
    public void deletePropertyById(long id) {
     propertyRepository.deleteById(id);
    }

    @Override
    public PropertyResponse updatePropertyById(long id, PropertyRequest propertyRequest) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
           modelmapper.map(propertyRequest, property);
            Property updatedProperty = propertyRepository.save(property);
            return modelmapper.map(updatedProperty, PropertyResponse.class);
        }
        return null;
    }

    @Override
    public List<Property> propertiesByAddress(String city) {
        return propertyRepository.findPropertyByAddress(city);
    }
@Override
public List<Property> propertiesByUserId(Long user_id) {
    Optional<User> optionalUser = userRepository.findById(Math.toIntExact(user_id));

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();

        if (Objects.equals(user.getRole(), Roles.OWNER)) {
            return propertyRepository.findAllByOwnerId(user_id);
        }
    }

    return new ArrayList<>(); // Return an empty list instead of null
}

    @Override
    public void cancelContingency(Long id) {
       updateToAvailable(id);
    }
    @Override
    public void approveContingency(long id){
       updateToSold(id);
    }

    @Override
    public Property updateToPending(Long id) {
        Optional<Property> isProperty=propertyRepository.findById(id);
        if(isProperty.isPresent()){
            var property=isProperty.get();
            property.setPropertyStatus(PropertyStatus.PENDING);

            propertyRepository.save(property);
            return property;
        }
        return null;
    }

    @Override
    public Property updateToAvailable(Long id) {
        Optional<Property> isProperty=propertyRepository.findById(id);
        if(isProperty.isPresent()){
            var property=isProperty.get();
            property.setPropertyStatus(PropertyStatus.AVAILABLE);

            propertyRepository.save(property);
            return property;
        }
        return null;
    }

    @Override
    public Property updateToContingent(Long id) {
        Optional<Property> isProperty=propertyRepository.findById(id);
        if(isProperty.isPresent()){
            var property=isProperty.get();
            property.setPropertyStatus(PropertyStatus.CONTINGENT);

            propertyRepository.save(property);
            return property;
        }
        return null;
    }

    @Override
    public Property updateToSold(Long id) {
        Optional<Property> isProperty=propertyRepository.findById(id);
        if(isProperty.isPresent()){
            var property=isProperty.get();
            property.setPropertyStatus(PropertyStatus.SOLD);

            propertyRepository.save(property);
            return property;
        }
        return null;
    }

}
