package project.furnitureworkshop.demo;

import org.apache.commons.codec.binary.Base64;
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
import project.furnitureworkshop.demo.controller.dto.WoodSpeccyDTO;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WoodSpeccyIntegrationIT {

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
    void verifyCreateWoodSpeccy() {
        RestTemplate restTemplate = new RestTemplate();
        WoodSpeccyDTO someWood = someWood();
        WoodSpeccyDTO updateWood = updateWood();
        String updateWoodType = updateWood.getWoodType();
        String updateHardness = updateWood.getHardness();
        String updateCubicMeterPrice = String.valueOf(updateWood.getCubicMeterPrice());

        // prepare request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //security
        String auth = "admin" + ":" + "security";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.add("Authorization", authHeader);

        HttpEntity<WoodSpeccyDTO> request = new HttpEntity<>(someWood, headers);
        HttpEntity<WoodSpeccyDTO> requestUpdate = new HttpEntity<>(updateWood, headers);

        // client creation
        ResponseEntity<Integer> forEntity = restTemplate.postForEntity("http://localhost:" + port + "/woods", request, Integer.class);
        Integer createdWoodSpeccyId = forEntity.getBody();

        //when
        ResponseEntity<WoodSpeccyDTO> actualWoodSpeccyEntity = restTemplate.exchange("http://localhost:" + port + "/woods/" + createdWoodSpeccyId, HttpMethod.GET, request, WoodSpeccyDTO.class);

        //update client
        updateWood.setId(createdWoodSpeccyId);
        ResponseEntity<WoodSpeccyDTO> updatedWoodSpeccy = restTemplate.exchange("http://localhost:" + port + "/woods/" + createdWoodSpeccyId, HttpMethod.PUT, requestUpdate, WoodSpeccyDTO.class);
        WoodSpeccyDTO updatedWoodSpeccyBody = updatedWoodSpeccy.getBody();

        //delete client
        restTemplate.exchange("http://localhost:" + port + "/woods/" + createdWoodSpeccyId, HttpMethod.DELETE, request, WoodSpeccyDTO.class);

        HttpClientErrorException.NotFound actualException = assertThrows(HttpClientErrorException.NotFound.class,
                () -> restTemplate.exchange("http://localhost:" + port + "/woods/" + createdWoodSpeccyId, HttpMethod.GET, request, WoodSpeccyDTO.class));

        String expectedMessage = String.format("404 : \"Wood not found: %d\"", createdWoodSpeccyId);

        //create wood then
        WoodSpeccyDTO actualWood = actualWoodSpeccyEntity.getBody();
        assertThat(actualWood).isNotNull();
        assertThat(actualWood.getWoodType()).isEqualTo(someWood.getWoodType());
        assertThat(actualWood.getHardness()).isEqualToIgnoringCase(someWood.getHardness());
        assertThat(actualWood.getCubicMeterPrice()).isEqualTo(someWood.getCubicMeterPrice());

        //update wood then
        assertThat(updatedWoodSpeccyBody.getWoodType()).isEqualTo(updateWoodType);
        assertThat(updatedWoodSpeccyBody.getHardness()).isEqualToIgnoringCase(updateHardness);
        assertThat(updatedWoodSpeccyBody.getCubicMeterPrice()).isEqualTo(updateCubicMeterPrice);

        //delete wood then
        assertThat(actualException.getMessage()).isEqualTo(expectedMessage);
    }

    private WoodSpeccyDTO updateWood() {
        WoodSpeccyDTO wood = new WoodSpeccyDTO();
        wood.setWoodType("Birch");
        wood.setHardness("HARD");
        wood.setCubicMeterPrice(new BigDecimal("4.00"));
        return wood;
    }

    private WoodSpeccyDTO someWood() {
        WoodSpeccyDTO wood = new WoodSpeccyDTO();
        wood.setWoodType("UpdateBirch");
        wood.setHardness("HARD");
        wood.setCubicMeterPrice(new BigDecimal("4.00"));
        return wood;
    }
}
