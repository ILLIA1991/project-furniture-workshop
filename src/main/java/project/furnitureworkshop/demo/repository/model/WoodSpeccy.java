package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wood_species")
public class WoodSpeccy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_of_wood")
    private String woodType;
    @Column(name = "hardness")
    @Enumerated(EnumType.STRING)
    private HardnessOfWood hardness;
    @Column(name = "price_cubic_meter")
    private BigDecimal cubicMeterPrice;

    public WoodSpeccy() {
    }

    public WoodSpeccy(BigDecimal cubicMeterPrice) {
        this.cubicMeterPrice = cubicMeterPrice;
    }

    public WoodSpeccy(String woodType, HardnessOfWood hardness, BigDecimal cubicMeterPrice) {
        this.woodType = woodType;
        this.hardness = hardness;
        this.cubicMeterPrice = cubicMeterPrice;
    }

    public WoodSpeccy(Integer id, String woodType, HardnessOfWood hardness, BigDecimal cubicMeterPrice) {
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

    public HardnessOfWood getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = HardnessOfWood.valueOf(hardness);
    }

    public BigDecimal getCubicMeterPrice() {
        return cubicMeterPrice;
    }

    public void setCubicMeterPrice(BigDecimal cubicMeterPrice) {
        this.cubicMeterPrice = cubicMeterPrice;
    }

    @Override
    public String toString() {
        return "WoodSpeccy{" +
                "id=" + id +
                ", type_of_wood='" + woodType + '\'' +
                ", hardness='" + hardness + '\'' +
                ", price_cubic_meter=" + cubicMeterPrice +
                '}';
    }
}
