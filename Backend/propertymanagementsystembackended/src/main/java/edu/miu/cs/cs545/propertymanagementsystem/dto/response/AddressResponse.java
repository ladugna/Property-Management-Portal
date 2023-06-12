package edu.miu.cs.cs545.propertymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressResponse {
    private String state;
    private String city;
    private String street;
    private String zipcode;
}
