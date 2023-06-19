package edu.miu.cs.cs545.propertymanagementsystem.service.impl;

import edu.miu.cs.cs545.propertymanagementsystem.dto.response.PropertyResponse;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.UserResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Property;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
import edu.miu.cs.cs545.propertymanagementsystem.repository.PropertyRepository;
import edu.miu.cs.cs545.propertymanagementsystem.repository.UserRepository;
import edu.miu.cs.cs545.propertymanagementsystem.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    private final ModelMapper modelMapper;
    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> users = new ArrayList<>();
        userRepository.findAllExceptAdmin().forEach(u -> {
            UserResponse user = modelMapper.map(u, UserResponse.class);
            user.setRole(u.getRole().get(0).getRoles());
            users.add(user);
        });
        return users;
    }

    @Override
    public List<UserResponse> getAllCustomer() {
       return findAll()
                .stream()
                .filter(lst -> lst.getRole().equals(Roles.CUSTOMER))
                .toList();

    }

    @Override
    public UserResponse getCustomerById(Integer id) {
        return modelMapper.map(userRepository.findById(id).orElse(null), UserResponse.class );
    }

    @Override
    public List<UserResponse> getAllOwners() {
        return findAll()
                .stream()
                .filter(lst -> lst.getRole().equals(Roles.OWNER))
                .toList();

    }

    @Override
    public UserResponse getOwnerById(Integer id) {
         return modelMapper.map(userRepository.findById(id).orElse(null), UserResponse.class);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void activateUser(long id) {
        userRepository.activateUser(id,"active");
    }

    @Override
    public void blockUser(long id) {
      userRepository.activateUser(id, "inactive");
    }

    @Override
    public void deleteUser(Integer id) {
      userRepository.deleteById(id);
    }

    @Override
    public List<PropertyResponse> getAllProperty() {
        return propertyRepository.findAll().stream()
                .map(e->modelMapper.map(e,PropertyResponse.class )).collect(Collectors.toList());
    }

    @Override
    public PropertyResponse getPropertyById(long id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            return modelMapper.map(property, PropertyResponse.class);
        }
        return null;
    }

    @Override
    public void deleteProperty(long id) {
        propertyRepository.deleteById(id, "PENDING");
    }

    @Override
    public void resetPassword(long id) {
   userRepository.resetPassword(id, "abc");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
