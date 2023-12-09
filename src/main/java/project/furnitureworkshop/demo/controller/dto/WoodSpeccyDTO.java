package project.furnitureworkshop.demo.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class WoodSpeccyDTO implements Serializable {

    private Integer id;

    private String woodType;

    private String hardness;
    private BigDecimal CubicMeterPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public BigDecimal getCubicMeterPrice() {
        return CubicMeterPrice;
    }

    public void setCubicMeterPrice(BigDecimal cubicMeterPrice) {
        this.CubicMeterPrice = cubicMeterPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WoodSpeccyDTO that = (WoodSpeccyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(woodType, that.woodType) && Objects.equals(hardness, that.hardness) && Objects.equals(CubicMeterPrice, that.CubicMeterPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, woodType, hardness, CubicMeterPrice);
    }

    @Override
    public String toString() {
        return "WoodSpeccyDTO{" +
                "id=" + id +
                ", woodType='" + woodType + '\'' +
                ", hardness='" + hardness + '\'' +
                ", CubicMeterPrice=" + CubicMeterPrice +
                '}';
    }
}

