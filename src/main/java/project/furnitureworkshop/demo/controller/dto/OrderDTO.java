package project.furnitureworkshop.demo.controller.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDTO {

    private Integer id;
    private ClientsDTO clients;
    private Date orderDate;
    private List<OrderItemDTO> ordersItems = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientsDTO getClients() {
        return clients;
    }

    public void setClients(ClientsDTO clients) {
        this.clients = clients;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemDTO> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<OrderItemDTO> ordersItems) {
        this.ordersItems = ordersItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(clients, orderDTO.clients) && Objects.equals(orderDate, orderDTO.orderDate) && Objects.equals(ordersItems, orderDTO.ordersItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clients, orderDate, ordersItems);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", clients=" + clients +
                ", dateOrder=" + orderDate +
                ", ordersItems=" + ordersItems +
                '}';
    }
}
