package com.oriontek.customerservice.query.handler;

import com.oriontek.customerservice.model.Customer;
import com.oriontek.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerQueryHandler {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllActiveCustomers() {
        // Usa el método que Spring Data JPA deriva para WHERE deletedAt IS NULL
        return customerRepository.findAllByDeletedAtIsNull();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        // Usa el método derivado para WHERE id = ? AND deletedAt IS NULL
        return customerRepository.findByIdAndDeletedAtIsNull(id);
    }
}
