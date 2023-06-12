package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressRequest {
    private String state;
    private String city;
    private String street;
    private String zipcode;
}
