package edu.miu.cs.cs545.propertymanagementsystem.repository;


import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("select f from Offer f join f.user u where u.user_id=:userId")
    List<Offer> findByUserId(Long userId);
    @Query("select o from Offer o where o.user.user_id=:cusId and o.offerStatus= 'PENDING'")
    List<Offer> findActiveOffersByCustomerId(Long cusId);

    @Query("select o from Offer o where o.offerStatus=:offerStatus")
    List<Offer> findByOfferStatus(OfferStatus offerStatus);

    @Query("select o from Offer o join o.property p where p.property_id=:propertyId")
    List<Offer> findOfferByPropertyId(Long propertyId);

}
