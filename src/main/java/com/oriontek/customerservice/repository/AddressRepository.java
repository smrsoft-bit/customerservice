package com.oriontek.customerservice.repository;

import com.oriontek.customerservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByDeletedAtIsNull(UUID customerId);
    Optional<Address> findByIdAndDeletedAtIsNull(UUID id);
}
