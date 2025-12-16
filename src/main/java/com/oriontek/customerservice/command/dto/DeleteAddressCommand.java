package com.oriontek.customerservice.command.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteAddressCommand(
        @NotNull(message = "Address ID is required for deletion.")
        UUID addressId
) {
}