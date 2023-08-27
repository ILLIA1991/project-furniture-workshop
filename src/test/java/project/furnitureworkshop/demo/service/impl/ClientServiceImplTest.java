package project.furnitureworkshop.demo.service.impl;

import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.converter.ClientConverter;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Client;
import project.furnitureworkshop.demo.service.ClientService;
import project.furnitureworkshop.demo.validator.ClientValidator;

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
    void shouldCreateClient() {
        //given
        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setName();
//        clientDTO.setSurname();
//        clientDTO.setPhone();
//        clientDTO.setEmail();
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
}