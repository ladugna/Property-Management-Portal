package edu.miu.cs.cs545.propertymanagementsystem.controller;


import edu.miu.cs.cs545.propertymanagementsystem.dto.response.UserResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import edu.miu.cs.cs545.propertymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer")
    public List<UserResponse> getAllCustomer(){
        return adminService.getAllCustomer();
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.getCustomerById(id), HttpStatus.OK);
    }

    //Owner

    @GetMapping("/owner")
    public ResponseEntity<?> getAllOwners(){
        return new ResponseEntity<>(adminService.getAllOwners(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.getOwnerById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveUser(@RequestBody User user) {
        adminService.saveUser(user);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/activate/{id}")
    public void activateUser(@PathVariable("id") long id) {
        adminService.activateUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/block/{id}")
    public void blockUser(@PathVariable("id") long id) {
        adminService.blockUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id")  Integer id) {
        adminService.deleteUser( id);
    }

    //Property

    @GetMapping("/property")
public ResponseEntity<?> getAllProperty(){
        return new ResponseEntity<>(adminService.getAllProperty(),HttpStatus.OK);
}

    @GetMapping("/property/{id}")

public ResponseEntity<?> getPropertyById(@PathVariable("id") long id){
        return new ResponseEntity<>(adminService.getPropertyById(id),HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/property/{id}")
    public void deleteProperty(@PathVariable("id")  long id) {
        adminService.deleteProperty(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/reset-password/{id}")
    public void resetPassword(@PathVariable("id") long id) {
        adminService.resetPassword(id);
    }
}