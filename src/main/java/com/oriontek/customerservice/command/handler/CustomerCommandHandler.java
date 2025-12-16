package com.oriontek.customerservice.command.handler;

import com.oriontek.customerservice.command.dto.CreateCustomerCommand;
import com.oriontek.customerservice.command.dto.DeleteCustomerCommand;
import com.oriontek.customerservice.command.dto.UpdateCustomerCommand;
import com.oriontek.customerservice.model.Customer;
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
public class CustomerCommandHandler {
    private final CustomerRepository customerRepository;

    public UUID handle(CreateCustomerCommand command) {
        Customer customer = Customer.builder()
                .name(command.name())
                .email(command.email())
                .phone(command.phone())
                .build();

        customer = customerRepository.save(customer);

        return customer.getId();
    }

    public void handle(UpdateCustomerCommand command) {
        log.info("Handling UpdateCustomerCommand for ID: ", command.getId());

        // 1. Buscar el cliente. Se usa findByIdAndDeletedAtIsNull para asegurar que
        //    solo se actualicen clientes no eliminados.
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(command.getId())
                .orElseThrow(() -> {
                    log.warn("Update failed: Customer ID {} not found or already deleted.", command.getId());
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Customer not found or already deleted."
                    );
                });

        // 2. Actualizar campos (Lógica de Negocio)
        customer.setName(command.getName());
        customer.setEmail(command.getEmail());
        customer.setPhone(command.getPhone());

        // El campo modifiedAt se actualiza automáticamente por @PreUpdate en BaseEntity.

        // 3. Persistir la actualización
        customerRepository.save(customer);

        log.info("Customer ID {} successfully updated.", customer.getId());
    }

    @Transactional
    public void handle(DeleteCustomerCommand command) {
        log.info("Handling DeleteCustomerCommand for ID: {}", command.getId());

        // 1. Buscar el cliente
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(command.getId())
                .orElseThrow(() -> {
                    log.warn("Delete failed: Customer ID {} not found or already deleted.", command.getId());
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Customer not found or already deleted."
                    );
                });

        // 2. Aplicar Soft Delete: Establecer la marca de tiempo de eliminación
        customer.setDeletedAt(Instant.now());

        // 3. Persistir el cambio
        customerRepository.save(customer);

        log.info("Customer ID  {} successfully soft-deleted.", customer.getId());
    }

}
