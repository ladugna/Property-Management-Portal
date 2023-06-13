package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.PropertyRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p where p.address.city like :location and p.number_of_bed_rooms =:numberOfBedrooms " +
            "and p.number_of_bath_rooms = :numberOfBathrooms and p.price = : price and p.status like : status")
    List<Property> findByCriteria(String location, int numberOfBedrooms, int numberOfBathrooms, double price, String status);


}
    
