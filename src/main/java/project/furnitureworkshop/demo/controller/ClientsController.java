package project.furnitureworkshop.demo.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.ClientsDTO;
import project.furnitureworkshop.demo.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsController.class);

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientsDTO> getAll() {
        LOGGER.debug("Greetings to you - the Great Lord of Java! A request has come to receive all customers!");
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientsDTO getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @PostMapping
    public Integer createUser(@RequestBody @Valid ClientsDTO clientsToCreate) {
        return clientService.createClient(clientsToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ClientsDTO update(@PathVariable Integer id, @RequestBody @Valid ClientsDTO clientsToUpdate) {
        return clientService.updateClient(id, clientsToUpdate);
    }
}
