package edu.miu.cs.cs545.propertymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is required")

    private String password;

    @NotBlank(message = "first name is Required")
    private String firstName;
    @NotBlank(message = "last name is Required")
    private String lastName;

    @NotBlank(message = "isOwner property is Required")

    private Boolean isOwner;
}
