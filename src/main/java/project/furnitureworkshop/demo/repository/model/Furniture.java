package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "material_cube_meter")
    private BigDecimal materialConsumption;

    public Furniture() {
    }

    public BigDecimal getMaterialConsumption() {
        return materialConsumption;
    }

    public void setMaterialConsumption(BigDecimal materialConsumption) {
        this.materialConsumption = materialConsumption;
    }


    public Furniture(String name, String description, BigDecimal materialConsumption) {
        this.name = name;
        this.description = description;
        this.materialConsumption = materialConsumption;
    }

    public Furniture(Integer id, String name, String description, BigDecimal materialConsumption) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.materialConsumption = materialConsumption;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", materialConsumption=" + materialConsumption +
                '}';
    }
}


