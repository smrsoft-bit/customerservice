package com.oriontek.customerservice.query.api;

import com.oriontek.customerservice.model.Customer;
import com.oriontek.customerservice.query.handler.CustomerQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/queries/address") // Nuevo path para consultas: /api/v1/queries/customers
@RequiredArgsConstructor
public class AddressQueryController {
    private final CustomerQueryHandler queryHandler;

    /**
     * Endpoint para obtener todos los clientes activos.
     * GET /queries/customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = queryHandler.getAllActiveCustomers();

        // Devuelve 200 OK con la lista de clientes (puede estar vacía)
        return ResponseEntity.ok(customers);
    }

    /**
     * Endpoint para obtener un cliente por su ID.
     * GET /queries/customers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {

        Customer customer = queryHandler.getCustomerById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found or is not active."
                ));

        // Devuelve 200 OK con el cliente
        return ResponseEntity.ok(customer);
    }
}
