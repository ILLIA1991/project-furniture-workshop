package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(name = "wood_id")
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

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
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
