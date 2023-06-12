package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
