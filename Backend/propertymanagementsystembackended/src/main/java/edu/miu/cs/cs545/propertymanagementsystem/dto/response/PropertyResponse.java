package edu.miu.cs.cs545.propertymanagementsystem.dto.response;

import edu.miu.cs.cs545.propertymanagementsystem.model.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PropertyResponse {
    private String title;
    private String description;
    private double price;
    private String number_of_bed_rooms;
    private String number_of_bath_rooms;
    private String image;
    private PropertyStatus propertyStatus;
    //private String status; //available, pending, contingent, sold
}
