package edu.miu.cs.cs545.propertymanagementsystem.controller;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.LoginRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.request.RefreshTokenRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.request.RegisterRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.LoginResponse;
import edu.miu.cs.cs545.propertymanagementsystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

}
