package com.oriontek.customerservice.command.handler;

import com.oriontek.customerservice.command.dto.*;
import com.oriontek.customerservice.model.Address;
import com.oriontek.customerservice.model.Customer;
import com.oriontek.customerservice.repository.AddressRepository;
import com.oriontek.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressCommandHandler {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public UUID handle(CreateAddressCommand command) {
        Customer customer = customerRepository.findById(command.customerId())
                .orElseThrow(() -> {
                    log.warn("Update failed: Customer ID {} not found or already deleted.", command.customerId());
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Customer not found or already deleted."
                    );
                });

        Address newAddress = Address.builder()
                .customer(customer)
                .name(command.name())
                .build();

        Address savedAddress = addressRepository.save(newAddress);

        return savedAddress.getId();
    }


    @Transactional
    public void handle(UpdateAddressCommand command) {
        Address address = addressRepository.findById(command.addressId())
                .orElseThrow(() -> {
                    log.warn("Update failed: Address ID {} not found or already deleted.", command.addressId());
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Address not found with ID: " + command.addressId()
                    );
                });

        // Ensure the customer ID is not changed unless necessary (optional check)
        if (!address.getCustomer().getId().equals(command.customerId())) {
            throw new IllegalArgumentException("Customer ID change not allowed for address update.");
        }

        address.setName(command.name());
        addressRepository.save(address); // save() for update is common in JPA
    }

    @Transactional
    public void handle(DeleteAddressCommand command) {
        Address address = addressRepository.findById(command.addressId())
                .orElseThrow(() -> {
                    log.warn("Delete failed: Address ID {} not found or already deleted.", command.addressId());
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Address not found with ID: " + command.addressId()
                    );
                });

        address.setDeletedAt(Instant.now());
        addressRepository.save(address);
    }

}
