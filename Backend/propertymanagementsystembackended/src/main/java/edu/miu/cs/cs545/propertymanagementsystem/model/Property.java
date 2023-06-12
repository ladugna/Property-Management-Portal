package edu.miu.cs.cs545.propertymanagementsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long property_id;
    private String title;
    private String description;
    private double price;
    private String number_of_bed_rooms;
    private String number_of_bath_rooms;
    private String image;
    private String status; //available, pending, contingent, sold
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
     @OneToMany
     private List<Offer> offers;


}
