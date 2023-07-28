package project.furnitureworkshop.demo.validator;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.ClientsDTO;
import project.furnitureworkshop.demo.exception.ValidationException;

@Component
public class ClientsValidator {

    public void validateClients(ClientsDTO clientsDTO) {
        if (!clientsDTO.getEmail().endsWith("@mail.com")) {
            throw new ValidationException("User email should be on @mail.com!");
        }
    }
}
