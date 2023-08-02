package project.furnitureworkshop.demo.validator;

import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.exception.ValidationException;
import project.furnitureworkshop.demo.repository.ClientRepository;
import project.furnitureworkshop.demo.repository.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ClientValidator {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    private static final Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\d+");

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void validateClients(ClientDTO clientDTO) {

        List<String> violations = new ArrayList<>();


        if (isBlank(clientDTO.getName())) {
            violations.add("Name is blank");
        }

        if (isBlank(clientDTO.getSurname())) {
            violations.add("Surname is blank");
        }

        if (!ONLY_LETTERS_PATTERN.matcher(clientDTO.getName()).matches()) {
            violations.add(String.format("%s can contain only letters: %s", "name", clientDTO.getName()));
        }
        if (!ONLY_LETTERS_PATTERN.matcher(clientDTO.getSurname()).matches()) {
            violations.add(String.format("%s can contain only letters: %s", "surname", clientDTO.getSurname()));
        }
        if (!PHONE_NUMBER_PATTERN.matcher(clientDTO.getPhone()).matches()) {
            violations.add(String.format("Field '%s' with value '%s' can contains only digits", "phone", clientDTO.getPhone()));
        }
        if (!EMAIL_PATTERN.matcher(clientDTO.getEmail()).matches()) {
            violations.add(String.format("Invalid email: %s", clientDTO.getEmail()));
        }
        List<Client> allByEmail = clientRepository.findAllByEmail(clientDTO.getEmail());
        if(!allByEmail.isEmpty()) {
            violations.add(String.format("email %s is already used in the system, please choose a different one!", clientDTO.getEmail()));
        }
        if(!violations.isEmpty()) {
            throw new ValidationException("Provided client is invalid", violations);
        }


    }
}
