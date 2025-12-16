package com.oriontek.customerservice.repository;

import com.oriontek.customerservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomerIdAndDeletedAtIsNull(Long customerId);
    Optional<Address> findByIdAndDeletedAtIsNull(Long id);
}
