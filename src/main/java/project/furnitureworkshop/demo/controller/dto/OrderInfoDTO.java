package project.furnitureworkshop.demo.controller.dto;

import java.math.BigDecimal;

public class OrderInfoDTO {

    private BigDecimal totalPriceInUsd;
    private BigDecimal totalPriceInEur;
    private FurnitureDTO furnitureId;
    private WoodSpeccyDTO woodId;
    private Integer quantity;

    public BigDecimal getTotalPriceInUsd() {
        return totalPriceInUsd;
    }

    public void setTotalPriceInUsd(BigDecimal totalPriceInUsd) {
        this.totalPriceInUsd = totalPriceInUsd;
    }

    public BigDecimal getTotalPriceInEur() {
        return totalPriceInEur;
    }

    public void setTotalPriceInEur(BigDecimal totalPriceInEur) {
        this.totalPriceInEur = totalPriceInEur;
    }

    public FurnitureDTO getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(FurnitureDTO furnitureId) {
        this.furnitureId = furnitureId;
    }

    public WoodSpeccyDTO getWoodId() {
        return woodId;
    }

    public void setWoodId(WoodSpeccyDTO woodId) {
        this.woodId = woodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "totalPriceInUsd=" + totalPriceInUsd +
                ", totalPriceInEur=" + totalPriceInEur +
                ", furnitureId=" + furnitureId +
                ", woodId=" + woodId +
                ", quantity=" + quantity +
                '}';
    }
}
