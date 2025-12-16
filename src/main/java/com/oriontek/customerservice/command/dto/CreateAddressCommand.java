package com.oriontek.customerservice.command.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAddressCommand(
        @NotNull(message = "Customer ID is required.")
        UUID customerId,

        @NotBlank(message = "Address name is required.")
        String name
) {
}