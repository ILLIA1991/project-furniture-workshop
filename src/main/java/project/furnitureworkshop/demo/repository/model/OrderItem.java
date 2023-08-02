package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn
    @ManyToOne
    private Order order;
    @JoinColumn
    @ManyToOne
    private Furniture furniture;
    @JoinColumn
    @ManyToOne
    private WoodSpeccy woodSpeccy;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public WoodSpeccy getWoodSpeccy() {
        return woodSpeccy;
    }

    public void setWoodSpeccy(WoodSpeccy woodSpeccy) {
        this.woodSpeccy = woodSpeccy;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
