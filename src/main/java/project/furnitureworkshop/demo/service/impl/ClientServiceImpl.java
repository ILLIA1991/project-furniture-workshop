package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.ClientsDTO;
import project.furnitureworkshop.demo.converter.ClientConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Clients;
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
    public List<ClientsDTO> getAllClients() {
        Collection<Clients> all = clientRepository.findAll();
        return clientConverter.convertToDto(all);
    }

    @Override
    public ClientsDTO getById(Integer id) {
        Clients clients = clientRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Client not found:" + id));
        return clientConverter.convertToDto(clients);
    }

    @Override
    @Transactional
    public Integer createClient(ClientsDTO clientsToCreate) {
        clientValidator.validateClients(clientsToCreate);
        Clients clients = clientConverter.convertToEntity(clientsToCreate);
        Clients savedClients = clientRepository.save(clients);
        return savedClients.getId();

    }

    @Override
    public void deleteById(Integer id) {
        Clients clients = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("You're not with us anymore!:" + id));
        clientRepository.delete(clients);
    }

    @Override
    @Transactional
    public ClientsDTO updateClient(Integer id, ClientsDTO clientsToUpdate) {
        Clients clients = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found:" + id));
        Clients entityToUpdate = clientConverter.convertToEntity(clientsToUpdate);
        entityToUpdate.setId(id);
        Clients updatedEntity = clientRepository.save(entityToUpdate);
        return clientConverter.convertToDto(updatedEntity);
    }
}
