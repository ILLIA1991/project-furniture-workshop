package project.furnitureworkshop.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class WoodSpeciesDTO implements Serializable {

    private Integer id;

    private String type_of_wood;

    private String hardness;
    private Double price_cubic_meter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_of_wood() {
        return type_of_wood;
    }

    public void setType_of_wood(String type_of_wood) {
        this.type_of_wood = type_of_wood;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public Double getPrice_cubic_meter() {
        return price_cubic_meter;
    }

    public void setPrice_cubic_meter(Double price_cubic_meter) {
        this.price_cubic_meter = price_cubic_meter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() ) return false;
        WoodSpeciesDTO that = (WoodSpeciesDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(type_of_wood, that.type_of_wood) && Objects.equals(hardness, that.hardness) && Objects.equals(price_cubic_meter, that.price_cubic_meter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_of_wood, hardness, price_cubic_meter);
    }
}
