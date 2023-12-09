package project.furnitureworkshop.demo;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientIntegrationIT {

    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @Test
    @DisplayName("Tests client creation")
    void verifyClientLifecycle() {
        //given
        RestTemplate restTemplate = new RestTemplate();
        ClientDTO someClient = someClient();
        ClientDTO updateClient = updateClient();
        String updatedClientName = updateClient.getName();
        String updatedClientPhone = updateClient.getPhone();
        String updatedClientEmail = updateClient.getEmail();

        // prepare request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // security
        String auth = "admin" + ":" + "security";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);

        HttpEntity<ClientDTO> request = new HttpEntity<>(someClient, headers);

        // client creation
        ResponseEntity<Integer> forEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", request, Integer.class);
        Integer createdClientId = forEntity.getBody();

        //when
        ResponseEntity<ClientDTO> actual = restTemplate.exchange("http://localhost:" + port + "/clients/" + createdClientId, HttpMethod.GET, request, ClientDTO.class);
        ClientDTO actualClient = actual.getBody();

        //create client then
        assertThat(actualClient).isNotNull();
        assertThat(actualClient.getName()).isEqualTo(someClient.getName());
        assertThat(actualClient.getSurname()).isEqualTo(someClient.getSurname());
        assertThat(actualClient.getPhone()).isEqualTo(someClient.getPhone());
        assertThat(actualClient.getEmail()).isEqualTo(someClient.getEmail());

        //update client
        HttpEntity<ClientDTO> requestUpdate = new HttpEntity<>(updateClient, headers);
        updateClient.setId(createdClientId);
        ResponseEntity<ClientDTO> updatedClient = restTemplate.exchange("http://localhost:" + port + "/clients/" + createdClientId, HttpMethod.PUT, requestUpdate, ClientDTO.class);
        ClientDTO updatedClientBody = updatedClient.getBody();

        //update client then
        assertThat(updatedClientBody.getName()).isEqualTo(updatedClientName);
        assertThat(updatedClientBody.getPhone()).isEqualTo(updatedClientPhone);
        assertThat(updatedClientBody.getEmail()).isEqualTo(updatedClientEmail);

        //delete client
        restTemplate.exchange("http://localhost:" + port + "/clients/" + createdClientId, HttpMethod.DELETE, request, ClientDTO.class);

        HttpClientErrorException.NotFound actualException = assertThrows(HttpClientErrorException.NotFound.class,
                () -> restTemplate.exchange("http://localhost:" + port + "/clients/" + createdClientId, HttpMethod.GET, request, ClientDTO.class));

        String expectedMessage = String.format("404 : \"Client not found: %d\"", createdClientId);

        //delete client then
        assertThat(actualException.getMessage()).isEqualTo(expectedMessage);
    }

    private ClientDTO updateClient() {
        ClientDTO client = new ClientDTO();
        client.setName("Richardas");
        client.setSurname("Hamondas");
        client.setPhone("111111111112");
        client.setEmail("t@mail.com");
        return client;
    }

    private ClientDTO someClient() {
        ClientDTO client = new ClientDTO();
        client.setName("Richard");
        client.setSurname("Hamond");
        client.setPhone("111111111113");
        client.setEmail("wt@mail.com");
        return client;
    }
}
