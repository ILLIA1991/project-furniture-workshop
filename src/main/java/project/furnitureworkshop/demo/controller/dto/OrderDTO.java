package project.furnitureworkshop.demo.controller.dto;

import project.furnitureworkshop.demo.repository.model.Order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDTO {

    private Integer id;
    private ClientsDTO clients;
    private Date dateOrder;
    private List<OrdersItemsDTO> ordersItems = new ArrayList<>();

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

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<OrdersItemsDTO> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<OrdersItemsDTO> ordersItems) {
        this.ordersItems = ordersItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(clients, orderDTO.clients) && Objects.equals(dateOrder, orderDTO.dateOrder) && Objects.equals(ordersItems, orderDTO.ordersItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clients, dateOrder, ordersItems);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", clients=" + clients +
                ", dateOrder=" + dateOrder +
                ", ordersItems=" + ordersItems +
                '}';
    }
}
