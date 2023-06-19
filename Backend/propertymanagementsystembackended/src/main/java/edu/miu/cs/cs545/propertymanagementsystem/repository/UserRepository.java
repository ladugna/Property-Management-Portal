package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.password = :value WHERE u.user_id= :id")
    public void resetPassword( long id, String value);

    @Query("select u from User u join u.role r where r.roles <> 'ADMIN'")
    List<User> findAllExceptAdmin();

    @Query("select u from User u join u.role r where r.roles = 'OWNER'")
    List<User> findAllUserByRoleOwner();
    @Query("select u from User u where u.role='CUSTOMER'")
    List<User> findAllCustomers();
    @Modifying
    @Query("UPDATE User u SET u.isActive = :value WHERE u.id = :id")
    void activateUser(long id, String value);
}
