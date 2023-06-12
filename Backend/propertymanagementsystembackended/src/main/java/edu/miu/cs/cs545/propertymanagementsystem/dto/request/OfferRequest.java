package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OfferRequest {
    private String title;
    private String description;
    private double price;
    private boolean status; //accepted or rejected
    private LocalDate expiration_date;
}
