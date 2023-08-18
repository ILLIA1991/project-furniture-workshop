package project.furnitureworkshop.demo.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("ilasemcenkov1@gmail.com");
        contact.setName("Illia Belikov");
        contact.setUrl("https://github.com/ILLIA1991");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/license/mit/");

        Info info = new Info()
                .title("Furniture Workshop Corporation (FWC)")
                .version("1.0")
                .contact(contact)
                .description("API fo Furniture Workshop Corporation")
                .license(mitLicense);

        return  new OpenAPI().info(info);
    }
}
