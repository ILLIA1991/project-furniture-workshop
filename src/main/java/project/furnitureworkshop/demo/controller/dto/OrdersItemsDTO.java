package project.furnitureworkshop.demo.controller.dto;

import java.sql.Date;

public class OrdersItemsDTO {

    private Integer id;
    private OrderDTO order;
    private FurnitureDTO furniture;
    private WoodSpeciesDTO woods;
    private Integer quantity;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public FurnitureDTO getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureDTO furniture) {
        this.furniture = furniture;
    }

    public WoodSpeciesDTO getWoods() {
        return woods;
    }

    public void setWoods(WoodSpeciesDTO woods) {
        this.woods = woods;
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
