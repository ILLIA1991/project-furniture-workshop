package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @Column(name = "date_order")
    private Date orderDate;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER

    )
    private List<OrderItem> ordersItems = new ArrayList<>();

    public Order() {
    }

    public Order(Client client, Date orderDate, List<OrderItem> ordersItems) {
        this.client = client;
        this.orderDate = orderDate;
        this.ordersItems = ordersItems;
    }

    public Order(Integer id, Client client, Date orderDate, List<OrderItem> ordersItems) {
        this.id = id;
        this.client = client;
        this.orderDate = orderDate;
        this.ordersItems = ordersItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClients() {
        return client;
    }

    public void setClients(Client client) {
        this.client = client;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<OrderItem> ordersItems) {
        this.ordersItems = ordersItems;
    }
}
