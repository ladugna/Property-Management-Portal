package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is required")

    private String password;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "isOwner property is Required")

    private Boolean isOwner;
}
