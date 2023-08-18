package project.furnitureworkshop.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.service.ClientService;

import java.util.List;
@Tag(name = "Client management API", description = "API for CRUD operations with clients")
@RestController
@RequestMapping("/clients")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @Operation(description = "Search for all clients . Only the admin has access to this operation.")
    @GetMapping
    public List<ClientDTO> getAll() {
        LOGGER.debug("Greetings to you - the Great Lord of Java! A request has come to receive all customers!");
        return clientService.getAllClients();
    }
    @Operation(description = "Search for a client by ID. Only the admin has access to this operation.")
    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }
    @Operation(description = "Registration of a new client. Anyone can do this, and the admin also has access rights.")
    @PostMapping
    public Integer createUser(@RequestBody ClientDTO clientsToCreate) {
        return clientService.createClient(clientsToCreate);
    }
    @Operation(description = "Delete a client by ID. This procedure can only be done by the admin.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }
    @Operation(description = "Updating customer data through a personal ID.")
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Integer id, @RequestBody ClientDTO clientsToUpdate) {
        return clientService.updateClient(id, clientsToUpdate);
    }
}
