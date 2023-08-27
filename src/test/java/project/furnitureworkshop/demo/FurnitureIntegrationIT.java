package project.furnitureworkshop.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FurnitureIntegrationIT {

    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.8");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @Test
    public void checkFurniture() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<FurnitureDTO[]> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/furniture", FurnitureDTO[].class);
        FurnitureDTO[] body = forEntity.getBody();

        assertThat(body).isNotEmpty();

    }

    @Test
    @DisplayName("Tests furniture creation")
    public void verifyCreateFurniture() {
        RestTemplate restTemplate = new RestTemplate();
        FurnitureDTO someFurniture = someFurniture();
        FurnitureDTO updateFurniture = updateFurniture();
        String updateFurnitureName = updateFurniture.getName();
        String updateFurnitureDescription = updateFurniture.getDescription();
        String updateFurnitureMaterialConsumption = String.valueOf(updateFurniture.getMaterialConsumption());

        // prepare request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FurnitureDTO> request = new HttpEntity<>(someFurniture, headers);
        HttpEntity<FurnitureDTO> requestUpdate = new HttpEntity<>(updateFurniture, headers);

        // client creation
        ResponseEntity<Integer> forEntity = restTemplate.postForEntity("http://localhost:" + port + "/furniture", request, Integer.class);
        Integer createdFurnitureId = forEntity.getBody();

        //when
        FurnitureDTO actualFurniture = restTemplate.getForObject("http://localhost:" + port + "/furniture/" + createdFurnitureId, FurnitureDTO.class);

        //update client
        updateFurniture.setId(createdFurnitureId);
        ResponseEntity<FurnitureDTO> updatedClient = restTemplate.exchange("http://localhost:" + port + "/furniture/" + createdFurnitureId, HttpMethod.PUT, requestUpdate, FurnitureDTO.class);
        FurnitureDTO updatedFurnitureBody = updatedClient.getBody();


        //delete client
        restTemplate.delete("http://localhost:" + port + "/clients/" + createdFurnitureId);


        HttpClientErrorException.NotFound actualException = assertThrows(HttpClientErrorException.NotFound.class,
                () -> restTemplate.getForObject("http://localhost:" + port + "/furniture/" + createdFurnitureId, FurnitureDTO.class));

        String expectedMessage =String.format("404 : \"Furniture not found: %d\"", createdFurnitureId);

        //create furniture then
        assertThat(actualFurniture).isNotNull();
        assertThat(actualFurniture.getName()).isEqualTo(someFurniture.getName());
        assertThat(actualFurniture.getDescription()).isEqualTo(someFurniture.getDescription());
        assertThat(actualFurniture.getMaterialConsumption()).isEqualTo(someFurniture.getMaterialConsumption());

        //update furniture then
        assert updatedFurnitureBody != null;
        assertThat(updatedFurnitureBody.getName()).isEqualTo(updateFurnitureName);
        assertThat(updatedFurnitureBody.getDescription()).isEqualTo(updateFurnitureDescription);
        assertThat(updatedFurnitureBody.getMaterialConsumption()).isEqualTo(updateFurnitureMaterialConsumption);

        //delete furniture then
        assertThat(actualException.getMessage()).isEqualTo(expectedMessage);

    }

    private FurnitureDTO updateFurniture() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("FurnitureName");
        dto.setDescription("DescriptionName");
        dto.setMaterialConsumption(BigDecimal.valueOf(5));
        return dto;
    }

    private FurnitureDTO someFurniture() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("FurnitureName");
        dto.setDescription("DescriptionName");
        dto.setMaterialConsumption(BigDecimal.valueOf(5));
        return dto;
    }
}
