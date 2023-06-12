package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PropertyRequest {
    private String title;
    private String description;
    private double price;
    private String number_of_bed_rooms;
    private String number_of_bath_rooms;
    private String image;
    private boolean status; //available, pending, contingent, sold
}
