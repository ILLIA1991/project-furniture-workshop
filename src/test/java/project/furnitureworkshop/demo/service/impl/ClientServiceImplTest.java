package project.furnitureworkshop.demo.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.converter.ClientConverter;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Client;
import project.furnitureworkshop.demo.service.ClientService;
import project.furnitureworkshop.demo.validator.ClientValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

class ClientServiceImplTest {


    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientConverter clientConverter = mock(ClientConverter.class);
    private final ClientValidator clientValidator= mock(ClientValidator.class);

    private final ClientService target = new ClientServiceImpl(clientRepository,
            clientConverter,
            clientValidator);

    @Test
    @DisplayName("Should create client")
    void shouldCreateClient() {
        //given
        ClientDTO clientDTO = new ClientDTO();
        Client clientToSave = new Client();
        Client savedClient = new Client(88);

        when(clientConverter.convertToEntity(clientDTO)).thenReturn(clientToSave);
        when(clientRepository.save(clientToSave)).thenReturn(savedClient);

        //when
        Integer actualId = target.createClient(clientDTO);

        //then
        verify(clientValidator).validateClient(clientDTO);
        verify(clientConverter).convertToEntity(clientDTO);

        assertThat(actualId).isEqualTo(savedClient.getId());

    }

    @Test
    @DisplayName("Should get all clients")
    void shouldGetAllClients() {
        //given
        Collection<Client> clients = new ArrayList<>();
        List<ClientDTO> expected = new ArrayList<>();

        when(clientRepository.findAll()).thenReturn((List<Client>) clients);
        when(clientConverter.convertToDto(clients)).thenReturn(expected);

        //when
        List<ClientDTO> actual = target.getAllClients();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should get client by id")
    void shouldGetClientById() {
        //given
        Integer id = 88;
        Client client = new Client();
        ClientDTO expected = new ClientDTO();

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientConverter.convertToDto(client)).thenReturn(expected);

        //when
        ClientDTO actual = target.getById(id);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should delete client by id")
    void shouldDeleteClientById() {
        //given
        Integer id = 88;
        Client client = new Client();

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        //when
        target.deleteById(id);

        //then
        verify(clientRepository).delete(client);
    }

    @Test
    @DisplayName("Should update client by id")
    void shouldUpdateClient() {
        //given
        Integer id = 88;
        ClientDTO clientDTO = new ClientDTO();
        Client clientToUpdate = new Client();
        Client updatedClient = new Client();
        ClientDTO expected = new ClientDTO();

        when(clientRepository.findById(id)).thenReturn(Optional.of(clientToUpdate));
        when(clientConverter.convertToEntity(clientDTO)).thenReturn(clientToUpdate);
        when(clientRepository.save(clientToUpdate)).thenReturn(updatedClient);
        when(clientConverter.convertToDto(updatedClient)).thenReturn(expected);

        //when
        ClientDTO actual = target.updateClient(id, clientDTO);

        //then
        verify(clientValidator).validateClient(clientDTO);
        verify(clientConverter).convertToEntity(clientDTO);

        assertThat(actual).isEqualTo(expected);
    }
}