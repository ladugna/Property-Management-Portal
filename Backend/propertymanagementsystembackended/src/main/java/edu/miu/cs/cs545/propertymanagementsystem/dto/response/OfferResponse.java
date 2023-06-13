package edu.miu.cs.cs545.propertymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OfferResponse {
    private String title;
    private String description;
    private double price;
    private String status; //accepted or rejected
    private LocalDate expiration_date;
}
