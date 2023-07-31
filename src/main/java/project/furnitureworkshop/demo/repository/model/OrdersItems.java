package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;
@Entity
@Table(name = "orders_items")
public class OrdersItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Furniture furniture;
    @ManyToOne
    private Wood_Species woodSpecies;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Wood_Species getWoodSpecies() {
        return woodSpecies;
    }

    public void setWoodSpecies(Wood_Species woodSpecies) {
        this.woodSpecies = woodSpecies;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
