package edu.miu.cs.cs545.propertymanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String status; //accepted or rejected
    private LocalDate expiration_date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "property_id")
    private Property property;

}
