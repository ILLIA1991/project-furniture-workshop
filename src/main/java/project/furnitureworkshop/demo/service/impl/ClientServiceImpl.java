package project.furnitureworkshop.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.furnitureworkshop.demo.controller.dto.ClientsDTO;
import project.furnitureworkshop.demo.converter.ClientsConverter;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.repository.ClientsRepository;
import project.furnitureworkshop.demo.repository.model.Clients;
import project.furnitureworkshop.demo.service.ClientService;
import project.furnitureworkshop.demo.validator.ClientsValidator;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientsRepository clientsRepository;
    private final ClientsConverter clientsConverter;

    private final ClientsValidator clientsValidator;


    public ClientServiceImpl(ClientsRepository clientsRepository, ClientsConverter clientsConverter, ClientsValidator clientsValidator) {
        this.clientsRepository = clientsRepository;
        this.clientsConverter = clientsConverter;
        this.clientsValidator = clientsValidator;
    }

    @Override
    public List<ClientsDTO> getAllClients() {
        Collection<Clients> all = clientsRepository.findAll();
        return clientsConverter.convertToDto(all);
    }

    @Override
    public ClientsDTO getById(Integer id) {
        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new FurnitureWorkshopNotFoundException("Client not found:" + id));
        return clientsConverter.convertToDto(clients);
    }

    @Override
    @Transactional
    public Integer createClient(ClientsDTO clientsToCreate) {
        clientsValidator.validateClients(clientsToCreate);
        Clients clients = clientsConverter.convertToEntity(clientsToCreate);
        Clients savedClients = clientsRepository.save(clients);
        return savedClients.getId();

    }

    @Override
    public void deleteById(Integer id) {
        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("You're not with us anymore!:" + id));
        clientsRepository.delete(clients);
    }

    @Override
    @Transactional
    public ClientsDTO updateClient(Integer id, ClientsDTO clientsToUpdate) {
        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found:" + id));
        Clients entityToUpdate = clientsConverter.convertToEntity(clientsToUpdate);
        entityToUpdate.setId(id);
        Clients updatedEntity = clientsRepository.save(entityToUpdate);
        return clientsConverter.convertToDto(updatedEntity);
    }
}
