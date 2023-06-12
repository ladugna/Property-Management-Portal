package edu.miu.cs.cs545.propertymanagementsystem.service;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PropertyService {

    public Page<PropertyResponse> findAll(Pageable pageable);
    public PropertyResponse findPropertyBYId(long id);
    public void addNewProperty(PropertyRequest propertyRequest);
    public void deletePropertyById(long id);
    public PropertyResponse updatePropertyById(long id, PropertyRequest propertyRequest);
}
