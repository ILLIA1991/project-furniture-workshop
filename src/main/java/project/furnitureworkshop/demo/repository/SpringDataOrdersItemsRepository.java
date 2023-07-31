package project.furnitureworkshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.furnitureworkshop.demo.repository.model.OrdersItems;
@Repository
public interface SpringDataOrdersItemsRepository extends JpaRepository<OrdersItems, Integer> {
}
