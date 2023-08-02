package project.furnitureworkshop.demo.service;

import project.furnitureworkshop.demo.controller.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO getById(Integer id);

    Integer createClient(ClientDTO clientDTO);

    void deleteById(Integer id);

    ClientDTO updateClient(Integer id, ClientDTO clientsToUpdate);

}
