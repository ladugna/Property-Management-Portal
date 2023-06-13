package edu.miu.cs.cs545.propertymanagementsystem.controller;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.service.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")

public class PropertyController {

    @Autowired
    PropertyServiceImpl propertyService;


    @GetMapping
    public Page<PropertyResponse> findAll(Pageable pageable) {

        return propertyService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public PropertyResponse findPropertyBYId(@PathVariable("id") long id){
        return propertyService.findPropertyBYId(id);
    }

    @PostMapping
    public void addNewProperty(@RequestBody PropertyRequest propertyRequest){
        propertyService.addNewProperty(propertyRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePropertyById(@PathVariable("id")long id){
        propertyService.deletePropertyById(id);
    }

    @PutMapping("/{id}")
    public void updatePropertyById(@PathVariable("id")long id, @RequestBody PropertyRequest propertyRequest){
        propertyService.updatePropertyById(id,propertyRequest);
    }

    @GetMapping
    public List<Property> findByCriteria(
            @RequestParam(value = "filter", required = false) String location,
            @RequestParam(value = "bedrooms", required = false) int numberOfBedrooms,
            @RequestParam(value = "bathrooms", required = false) int numberOfBathrooms,
            @RequestParam(value = "price", required = false) double price,
            @RequestParam(value = "status", required = false) String status) {

        return propertyService.findByCriteria(location,numberOfBedrooms,numberOfBathrooms,price,status);
    }



}
