package edu.miu.cs.cs545.propertymanagementsystem.service.impl;


import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.service.PropertyService;
import edu.miu.cs.cs545.propertymanagementsystem.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {


    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public Page<PropertyResponse> findAll(Pageable pageable) {

        return modelMapper.map(propertyRepository.findAll(pageable), Page.class);
    }

    @Override
    public PropertyResponse findPropertyBYId(long id) {
        return null;
    }

    @Override
    public PropertyResponse findPropertyById(long id) {

        return modelMapper.map(propertyRepository.findById(id), PropertyResponse.class);
    }

    @Override
    public void addNewProperty(PropertyRequest propertyRequest) {

    }

    @Override
    public void deletePropertyById(long id) {
       propertyRepository.deleteById(id);
    }

    @Override
    public void updatePropertyById(long id, PropertyRequest propertyRequest) {

       var property = propertyRepository.findById(id).orElseThrow();
       property.setDescription(propertyRequest.getDescription());
       property.setImage(propertyRequest.getImage());
       property.setDescription(propertyRequest.getDescription());
       property.setPrice(propertyRequest.getPrice());
       property.setNumber_of_bath_rooms(propertyRequest.getNumber_of_bath_rooms());
       property.setNumber_of_bed_rooms(propertyRequest.getNumber_of_bed_rooms());
       property.setTitle(propertyRequest.getTitle());

      propertyRepository.save(property);
    }

    @Override
    public List<Property> findByCriteria(String location,
                                         int numberOfBedrooms,
                                         int numberOfBathrooms,
                                         double price,
                                         String status) {
        return propertyRepository.findByCriteria(location,numberOfBedrooms,numberOfBathrooms,price,status);
    }


}
