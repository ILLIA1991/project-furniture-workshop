package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.converter.ClientConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Client;
import project.furnitureworkshop.demo.service.ClientService;
import project.furnitureworkshop.demo.validator.ClientValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    private final ClientValidator clientValidator;

    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter, ClientValidator clientValidator) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
        this.clientValidator = clientValidator;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        Collection<Client> all = clientRepository.findAll();
        return clientConverter.convertToDto(all);
    }

    @Override
    public ClientDTO getById(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Client not found: " + id));
        return clientConverter.convertToDto(client);
    }

    @Override
    @Transactional
    public Integer createClient(ClientDTO clientsToCreate) {
        clientValidator.validateClient(clientsToCreate);
        Client client = clientConverter.convertToEntity(clientsToCreate);
        Client savedClient = clientRepository.save(client);
        return savedClient.getId();

    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("You're not with us anymore!: " + id));
        clientRepository.delete(client);
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Integer id, ClientDTO clientsToUpdate) {
        clientValidator.validateClient(clientsToUpdate);
        Client client = clientRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Client not found: " + id));
        Client entityToUpdate = clientConverter.convertToEntity(clientsToUpdate);
        entityToUpdate.setId(id);
        Client updatedEntity = clientRepository.save(entityToUpdate);
        return clientConverter.convertToDto(updatedEntity);
    }
}
