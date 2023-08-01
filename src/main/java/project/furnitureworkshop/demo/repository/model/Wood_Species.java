package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wood_species")
public class Wood_Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_of_wood")
    private String woodType;
    @Column(name = "hardness")
    private String hardness;
    @Column(name = "price_cubic_meter")
    private BigDecimal cubicMeterPrice;



    public Wood_Species(String woodType, String hardness, BigDecimal cubicMeterPrice) {
        this.woodType = woodType;
        this.hardness = hardness;
        this.cubicMeterPrice = cubicMeterPrice;
    }

    public Wood_Species(Integer id, String woodType, String hardness, BigDecimal cubicMeterPrice) {
        this.id = id;
        this.woodType = woodType;
        this.hardness = hardness;
        this.cubicMeterPrice = cubicMeterPrice;
    }

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
        return cubicMeterPrice;
    }

    public void setCubicMeterPrice(BigDecimal cubicMeterPrice) {
        this.cubicMeterPrice = cubicMeterPrice;
    }

    @Override
    public String toString() {
        return "Wood_Species{" +
                "id=" + id +
                ", type_of_wood='" + woodType + '\'' +
                ", hardness='" + hardness + '\'' +
                ", price_cubic_meter=" + cubicMeterPrice +
                '}';
    }
}
