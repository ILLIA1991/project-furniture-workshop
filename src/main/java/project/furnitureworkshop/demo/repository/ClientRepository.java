package project.furnitureworkshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.furnitureworkshop.demo.repository.model.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer> {


}
