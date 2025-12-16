package com.oriontek.customerservice.command.api;

import com.oriontek.customerservice.command.dto.*;
import com.oriontek.customerservice.command.handler.AddressCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/commands/address")
@RequiredArgsConstructor
public class AddressCommandController {

    private final AddressCommandHandler addressCommandHandler;

    @PostMapping
    public ResponseEntity<UUID> createAddress(@Valid @RequestBody CreateAddressCommand command) {
        UUID newId = addressCommandHandler.handle(command);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(
            @PathVariable UUID addressId,
            @Valid @RequestBody UpdateAddressRequest request) { // Uses a DTO for body data

        // 1. Create the canonical command from path and body data
        UpdateAddressCommand command = new UpdateAddressCommand(
                addressId,
                request.customerId(),
                request.name()
        );

        addressCommandHandler.handle(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID addressId) {
        DeleteAddressCommand command = new DeleteAddressCommand(addressId);
        addressCommandHandler.handle(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

// Helper Record for PUT/UPDATE request body structure
record UpdateAddressRequest(UUID customerId, String name) {}