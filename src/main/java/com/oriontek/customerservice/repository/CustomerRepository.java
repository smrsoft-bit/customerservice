package com.oriontek.customerservice.repository;

import com.oriontek.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByDeletedAtIsNull();
    java.util.Optional<Customer> findByIdAndDeletedAtIsNull(UUID id);
}
