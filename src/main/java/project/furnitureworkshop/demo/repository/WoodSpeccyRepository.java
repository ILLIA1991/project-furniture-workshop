package project.furnitureworkshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.furnitureworkshop.demo.repository.model.Wood_Species;
@Repository
public interface WoodSpeccyRepository extends JpaRepository<Wood_Species, Integer> {
}
