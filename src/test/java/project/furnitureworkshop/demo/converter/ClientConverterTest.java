package project.furnitureworkshop.demo.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.repository.model.Client;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ClientConverterTest {

    private static final String NAME = "TestName";
    private static final String SURNAME = "TestSurname";
    private static final String PHONE = "911629596315";
    private static final String EMAIL = "t@mail.com";
    private static final Integer ID = 1234;
    private final ClientConverter target = new ClientConverter();

    @Test
    @DisplayName("Should convert ClientDTO to Client")
    void shouldConvertClientDtoToEntity() {
        //given
        ClientDTO client = clientDTO();
        Client expected = entityClient();

        //when
        Client actual = target.convertToEntity(client);

        //then
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getSurname()).isEqualTo(expected.getSurname());
        assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
    }

    @Test
    @DisplayName("Should convert Client to DTO")
    void shouldConvertClientToDto() {
        //given
        Client client = entityClient();
        ClientDTO expected = clientDTO();

        //when
        ClientDTO actual = target.convertToDto(client);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should convert Collection<Clients> to List<ClientDTO>")
    void shouldConvertCollectionClientsToListClientsDTO() {
        //given
        Collection<Client> clients = List.of(entityClient());
        List<ClientDTO> expected = List.of(clientDTO());

        //when
        List<ClientDTO> actual = target.convertToDto(clients);

        //then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    private Client entityClient() {
        Client client = new Client();
        client.setId(ClientConverterTest.ID);
        client.setName(NAME);
        client.setSurname(SURNAME);
        client.setPhone(PHONE);
        client.setEmail(EMAIL);
        return client;
    }

    private ClientDTO clientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(ClientConverterTest.ID);
        clientDTO.setName(NAME);
        clientDTO.setSurname(SURNAME);
        clientDTO.setPhone(PHONE);
        clientDTO.setEmail(EMAIL);
        return clientDTO;
    }
}