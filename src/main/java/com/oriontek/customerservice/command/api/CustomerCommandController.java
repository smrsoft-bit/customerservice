package com.oriontek.customerservice.command.api;

import com.oriontek.customerservice.command.dto.CreateCustomerCommand;
import com.oriontek.customerservice.command.dto.DeleteCustomerCommand;
import com.oriontek.customerservice.command.dto.UpdateCustomerCommand;
import com.oriontek.customerservice.command.handler.CustomerCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/commands/customers")
@RequiredArgsConstructor
public class CustomerCommandController {
    private final CustomerCommandHandler commandHandler;

    /**
     * Endpoint para crear un nuevo cliente.
     * POST /commands/customers
     */
    @PostMapping
    public ResponseEntity<UUID> createCustomer(@Valid @RequestBody CreateCustomerCommand command) {

        // 1. El Controller recibe el DTO validado.
        // 2. Llama al Handler para ejecutar la lógica de negocio.
        UUID newCustomerId = commandHandler.handle(command);

        // 3. Devuelve la respuesta 201 Created junto con el ID del nuevo recurso.
        return new ResponseEntity<>(newCustomerId, HttpStatus.CREATED);
    }

    /**
     * Endpoint para actualizar un cliente existente.
     * PUT /commands/customers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCustomerCommand request) {

        // 1. Construir el Command DTO completo a partir del PathVariable y el Body
        UpdateCustomerCommand command = UpdateCustomerCommand.builder()
                .id(id) // Usar el ID del path para asegurar que la acción es sobre ese recurso
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        // 2. Llamar al Handler
        commandHandler.handle(command);

        // 3. Devuelve 204 No Content para una actualización exitosa sin cuerpo de respuesta.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para eliminar (soft delete) un cliente.
     * DELETE /commands/customers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {

        // 1. Construir el Command DTO
        DeleteCustomerCommand command = DeleteCustomerCommand.builder()
                .id(id)
                .build();

        // 2. Llamar al Handler
        commandHandler.handle(command);

        // 3. Devuelve 204 No Content.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
