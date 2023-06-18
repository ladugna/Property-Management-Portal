package edu.miu.cs.cs545.propertymanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long address_id;
    private String state;
    private String city;
    private String street;
    private String zipcode;
    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Property property;

}
