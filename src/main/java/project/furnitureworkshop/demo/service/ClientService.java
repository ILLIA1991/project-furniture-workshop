package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.ClientsDTO;

import java.util.List;

public interface ClientService {

    List<ClientsDTO> getAllClients();

    ClientsDTO getById(Integer id);

    Integer createClient(ClientsDTO clientsDTO);

    void deleteById(Integer id);

    ClientsDTO updateClient(Integer id, ClientsDTO clientsToUpdate);

}
