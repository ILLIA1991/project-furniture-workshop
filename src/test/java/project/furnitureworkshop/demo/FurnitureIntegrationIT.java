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
import project.furnitureworkshop.demo.controller.dto.FurnitureDTO;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FurnitureIntegrationIT {

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
    @DisplayName("Tests furniture creation")
    void verifyCreateFurniture() {
        RestTemplate restTemplate = new RestTemplate();
        FurnitureDTO someFurniture = someFurniture();
        FurnitureDTO updateFurniture = updateFurniture();
        String updateFurnitureName = updateFurniture.getName();
        String updateFurnitureDescription = updateFurniture.getDescription();
        String updateFurnitureMaterialConsumption = String.valueOf(updateFurniture.getMaterialConsumption());

        // prepare request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //security
        String auth = "admin" + ":" + "security";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);

        HttpEntity<FurnitureDTO> request = new HttpEntity<>(someFurniture, headers);
        HttpEntity<FurnitureDTO> requestUpdate = new HttpEntity<>(updateFurniture, headers);

        // furniture creation
        ResponseEntity<Integer> forEntity = restTemplate.postForEntity("http://localhost:" + port + "/furniture", request, Integer.class);
        Integer createdFurnitureId = forEntity.getBody();

        //when
        ResponseEntity<FurnitureDTO> actualFurnitureEntity = restTemplate.exchange("http://localhost:" + port + "/furniture/" + createdFurnitureId, HttpMethod.GET, request, FurnitureDTO.class);

        //update furniture
        updateFurniture.setId(createdFurnitureId);
        ResponseEntity<FurnitureDTO> updatedFurniture = restTemplate.exchange("http://localhost:" + port + "/furniture/" + createdFurnitureId, HttpMethod.PUT, requestUpdate, FurnitureDTO.class);
        FurnitureDTO updatedFurnitureBody = updatedFurniture.getBody();

        //delete furniture
        restTemplate.exchange("http://localhost:" + port + "/furniture/" + createdFurnitureId, HttpMethod.DELETE, request, FurnitureDTO.class);

        HttpClientErrorException.NotFound actualException = assertThrows(HttpClientErrorException.NotFound.class,
                () -> restTemplate.exchange("http://localhost:" + port + "/furniture/" + createdFurnitureId, HttpMethod.GET, request, FurnitureDTO.class));

        String expectedMessage = String.format("404 : \"Furniture not found: %d\"", createdFurnitureId);

        //create furniture then
        FurnitureDTO actualFurniture = actualFurnitureEntity.getBody();
        assertThat(actualFurniture).isNotNull();
        assertThat(actualFurniture.getName()).isEqualTo(someFurniture.getName());
        assertThat(actualFurniture.getDescription()).isEqualTo(someFurniture.getDescription());
        assertThat(actualFurniture.getMaterialConsumption()).isEqualTo(someFurniture.getMaterialConsumption());

        //update furniture then
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
        dto.setMaterialConsumption(new BigDecimal("5.00"));
        return dto;
    }

    private FurnitureDTO someFurniture() {
        FurnitureDTO dto = new FurnitureDTO();
        dto.setName("FurnitureName");
        dto.setDescription("DescriptionName");
        dto.setMaterialConsumption(new BigDecimal("5.00"));
        return dto;
    }
}
