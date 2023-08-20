package project.furnitureworkshop.demo.validator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Client;


import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientValidatorTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientValidator clientValidator;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clientValidator = new ClientValidator(clientRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testValidateClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("1234567890");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        assertDoesNotThrow(() -> clientValidator.validateClient(clientDTO));
    }

    @Test
    public void testValidateClientWithBlankName() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("1234567890");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        ValidationException exception = assertThrows(ValidationException.class, () -> clientValidator.validateClient(clientDTO));
        assertFalse(exception.getMessage().contains("name is blank"));
    }

    @Test
    public void testValidateClientWithInvalidName() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John123");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("1234567890");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        ValidationException exception = assertThrows(ValidationException.class, () -> clientValidator.validateClient(clientDTO));
        assertFalse(exception.getMessage().contains("name can contain only letters: 'John123'"));
    }

    @Test
    public void testValidateClientWithBlankPhone() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        ValidationException exception = assertThrows(ValidationException.class, () -> clientValidator.validateClient(clientDTO));
        assertFalse(exception.getMessage().contains("Phone is blank"));
    }

    @Test
    public void testValidateClientWithInvalidPhone() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("123-456-7890");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        ValidationException exception = assertThrows(ValidationException.class, () -> clientValidator.validateClient(clientDTO));
        assertFalse(exception.getMessage().contains("phone can contain only digits: '123-456-7890'"));
    }

    @Test
    public void testValidateClientWithInvalidEmail() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("1234567890");
        clientDTO.setEmail("john.doe@invalid");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of());

        ValidationException exception = assertThrows(ValidationException.class, () ->
                clientValidator.validateClient(clientDTO));
        assertFalse(exception.getMessage().contains(String.format(
                "invalid email: '%s'", "john.doe@invalid")));
    }

    @Test
    public void testValidateClientWithEmailAlreadyInUse() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setSurname("Doe");
        clientDTO.setPhone("1234567890");
        clientDTO.setEmail("john.doe@example.com");

        when(clientRepository.findAllByEmail(anyString())).thenReturn(List.of(new
                Client()));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                clientValidator.validateClient(clientDTO));
        assertTrue(exception.getMessage().contains(String.format(
                "email '%s' is already used in the system. Please choose a different one!",
                "john.doe@example.com")));
    }
}