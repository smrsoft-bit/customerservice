package com.oriontek.customerservice.command.dto;

import java.util.List;
import java.util.UUID;

public record CreateCustomerCommand(
        String name,
        String email,
        String phone,
        List<AddressDto> addresses
) {
    public record AddressDto(UUID customer_id, String name) {}
}