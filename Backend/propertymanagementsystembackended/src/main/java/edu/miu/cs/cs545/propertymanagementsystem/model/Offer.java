package edu.miu.cs.cs545.propertymanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offer_id;
    private String title;
    private String description;
    private double price;


   // private String status; //accepted or rejected-
    private LocalDate expiration_date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "property_id")
    @JsonManagedReference
    private Property property;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
   private User user;


}
