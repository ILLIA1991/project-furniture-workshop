package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders")
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    @Column(name = "date_order")
    private Date orderDate;

    @OneToMany(
            mappedBy = "orders",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER

    )
    private List<OrderItem> ordersItems = new ArrayList<>();

    public Orders() {
    }

    public Orders(Client client, Date orderDate, List<OrderItem> ordersItems) {
        this.client = client;
        this.orderDate = orderDate;
        this.ordersItems = ordersItems;
    }

    public Orders(Integer id, Client client, Date orderDate, List<OrderItem> ordersItems) {
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
