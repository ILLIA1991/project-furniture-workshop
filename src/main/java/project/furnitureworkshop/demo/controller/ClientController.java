package project.furnitureworkshop.demo.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAll() {
        LOGGER.debug("Greetings to you - the Great Lord of Java! A request has come to receive all customers!");
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @PostMapping
    public Integer createUser(@RequestBody ClientDTO clientsToCreate) {
        return clientService.createClient(clientsToCreate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Integer id, @RequestBody ClientDTO clientsToUpdate) {
        return clientService.updateClient(id, clientsToUpdate);
    }
}
