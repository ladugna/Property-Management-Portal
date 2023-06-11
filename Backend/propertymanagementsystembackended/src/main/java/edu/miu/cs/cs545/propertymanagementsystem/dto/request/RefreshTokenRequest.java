package edu.miu.cs.cs545.propertymanagementsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RefreshTokenRequest {
    private String accessToken;
    private String refreshToken;
}
