package com.oriontek.customerservice.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateCustomerCommand {
    @NotNull(message = "Customer ID is required for update.")
    private UUID id;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Phone number cannot be empty.")
    // Asumiendo un formato de 10 dígitos, ajustar según necesidad local
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.")
    private String phone;
}
