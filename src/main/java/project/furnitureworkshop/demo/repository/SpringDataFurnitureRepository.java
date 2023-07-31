package project.furnitureworkshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.furnitureworkshop.demo.repository.model.Furniture;

@Repository
public interface SpringDataFurnitureRepository extends JpaRepository<Furniture, Integer> {
}
