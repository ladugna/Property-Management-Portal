package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Builder
public class LoginRequest {
    private String email;
    private String password;
}
