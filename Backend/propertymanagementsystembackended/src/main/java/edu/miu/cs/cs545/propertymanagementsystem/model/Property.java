package edu.miu.cs.cs545.propertymanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;
   // private String status; //available, pending, contingent, sold

    @Enumerated(EnumType.STRING)
   private PropertyStatus propertyStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;
    @OneToMany
    @JsonBackReference
    private List<Offer> offers;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;


}
