package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteList, Long> {

    @Query("select f from FavoriteList f join f.customer c where c.user_id=:customerId")
    List<FavoriteList> findByCustomerId(Long customerId);
    @Query("select f from FavoriteList f join f.customer c join f.properties p where " +
            "c.user_id=:customerId and p.property_id=:propertyId")
    Optional<FavoriteList> findByCustomerIdAndPropertiesId(Long customerId, Long propertyId);
}
