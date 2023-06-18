package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Modifying
    @Query("delete from Property p where p.property_id=:propId and p.propertyStatus <> :status")
    void deleteById(long propId, String status);
    @Query("select p from Property  p where p.address.city=?1")
    public List<Property> findPropertyByAddress(String city);

    @Query("select p from Property p where p.property_id=:ownerId")
    List<Property> findAllByOwnerId(Long ownerId);
}
