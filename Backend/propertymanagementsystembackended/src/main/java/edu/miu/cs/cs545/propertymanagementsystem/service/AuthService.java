package edu.miu.cs.cs545.propertymanagementsystem.service;


import edu.miu.cs.cs545.propertymanagementsystem.dto.request.LoginRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.request.RefreshTokenRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
