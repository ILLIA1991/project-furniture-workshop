package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.dto.ClientDTO;
import project.furnitureworkshop.demo.repository.model.Client;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {

    public Client convertToEntity(ClientDTO source) {
        return new Client(source.getId(),
                source.getName(),
                source.getSurname(),
                source.getEmail(),
                source.getPhone());
    }

    public ClientDTO convertToDto(Client source) {
        return convertClientsToDto(source);
    }

    public List<ClientDTO> convertToDto(Collection<Client> source) {
        return source.stream()
                .map(this::convertClientsToDto)
                .collect(Collectors.toList());
    }

    private ClientDTO convertClientsToDto(Client source) {
        ClientDTO result = new ClientDTO();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setSurname(source.getSurname());
        result.setEmail(source.getEmail());
        result.setPhone(source.getPhone());
        return result;
    }


}
