package project.furnitureworkshop.demo.converter;

import org.springframework.stereotype.Component;
import project.furnitureworkshop.demo.controller.ClientsDTO;
import project.furnitureworkshop.demo.repository.Clients;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientsConverter {

    public Clients convertToEntity(ClientsDTO source) {
        return new Clients(source.getId(),
                source.getName(),
                source.getSurname(),
                source.getEmail(),
                source.getPhone());
    }

    public ClientsDTO convertToDto(Clients source) {
        return convertClientsToDto(source);
    }

    public List<ClientsDTO> convertToDto(Collection<Clients> source) {
        return source.stream()
                .map(this::convertClientsToDto)
                .collect(Collectors.toList());
    }

    private ClientsDTO convertClientsToDto(Clients source) {
        ClientsDTO result = new ClientsDTO();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setSurname(source.getSurname());
        result.setEmail(source.getEmail());
        result.setPhone(source.getPhone());
        return result;
    }


}
