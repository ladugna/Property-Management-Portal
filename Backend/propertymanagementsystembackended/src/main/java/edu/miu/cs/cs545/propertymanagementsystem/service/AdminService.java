package edu.miu.cs.cs545.propertymanagementsystem.service;



import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.UserResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {

    List<UserResponse> findAll();


    List<UserResponse> getAllCustomer();

    //Page<User> pagination(PagingRequest pagingRequest);

    UserResponse getCustomerById(Integer id);

    List<UserResponse> getAllOwners();

    UserResponse getOwnerById(Integer id);

    void saveUser(User user);

    void activateUser(long id);

    void blockUser(long id);

    void deleteUser(Integer id);

    List<PropertyResponse> getAllProperty();

    PropertyResponse getPropertyById(long id);

    void deleteProperty(long id);

    void resetPassword(long id);
}