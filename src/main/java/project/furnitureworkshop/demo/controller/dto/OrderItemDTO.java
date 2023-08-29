package project.furnitureworkshop.demo.controller.dto;

import java.math.BigDecimal;

public class OrderItemDTO {

    private Integer id;
    private FurnitureDTO furniture;
    private WoodSpeccyDTO wood;
    private Integer quantity;
    private BigDecimal price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public FurnitureDTO getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureDTO furniture) {
        this.furniture = furniture;
    }

    public WoodSpeccyDTO getWood() {
        return wood;
    }

    public void setWood(WoodSpeccyDTO wood) {
        this.wood = wood;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setWoodSpeccy(WoodSpeccyDTO woodSpeccy) {
    }
}
