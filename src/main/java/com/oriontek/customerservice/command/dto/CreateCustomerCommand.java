package com.oriontek.customerservice.command.dto;

import java.util.List;

public record CreateCustomerCommand(
        String name,
        String email,
        String phone,
        List<AddressDto> addresses
) {
    public record AddressDto(String street, String city) {}
}