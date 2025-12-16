package com.oriontek.customerservice.query.handler;

import com.oriontek.customerservice.model.Address;
import com.oriontek.customerservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressQueryHandler {
    private final AddressRepository addressRepository;

    public List<Address> getAllActiveAddreses(UUID customerId) {
        // Usa el método que Spring Data JPA deriva para WHERE deletedAt IS NULL
        return addressRepository.findAllByDeletedAtIsNull(customerId);
    }

    public Optional<Address> getAddressById(UUID id) {
        // Usa el método derivado para WHERE id = ? AND deletedAt IS NULL
        return addressRepository.findByIdAndDeletedAtIsNull(id);
    }
}
