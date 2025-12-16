package com.oriontek.customerservice.command.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateAddressCommand(
        @NotNull(message = "Address ID is required for update.")
        UUID addressId,

        @NotNull(message = "Customer ID is required.")
        UUID customerId,

        @NotBlank(message = "Address name cannot be empty.")
        @Size(max = 100, message = "Address name cannot exceed 100 characters.")
        String name
) {
    // Note: All fields must be validated here to ensure the update payload is valid.
}