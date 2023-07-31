package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wood_species")
public class Wood_Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_of_wood")
    private String type_of_wood;
    @Column(name = "hardness")
    private String hardness;
    @Column(name = "price_cubic_meter")
    private Double price_cubic_meter;

    public Wood_Species() {
    }

    public Wood_Species(String type_of_wood, String hardness, Double price_cubic_meter) {
        this.type_of_wood = type_of_wood;
        this.hardness = hardness;
        this.price_cubic_meter = price_cubic_meter;
    }

    public Wood_Species(Integer id, String type_of_wood, String hardness, Double price_cubic_meter) {
        this.id = id;
        this.type_of_wood = type_of_wood;
        this.hardness = hardness;
        this.price_cubic_meter = price_cubic_meter;
    }

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
    public String toString() {
        return "Wood_Species{" +
                "id=" + id +
                ", type_of_wood='" + type_of_wood + '\'' +
                ", hardness='" + hardness + '\'' +
                ", price_cubic_meter=" + price_cubic_meter +
                '}';
    }
}
