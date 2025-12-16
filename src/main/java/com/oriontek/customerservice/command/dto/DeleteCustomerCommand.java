package com.oriontek.customerservice.command.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteCustomerCommand {
    @NotNull(message = "Customer ID is required for deletion.")
    private UUID id;
}