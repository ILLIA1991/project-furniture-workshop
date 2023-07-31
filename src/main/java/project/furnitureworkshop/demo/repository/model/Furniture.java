package project.furnitureworkshop.demo.repository.model;

import jakarta.persistence.*;

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
    private Double material_cube_meter;

    public Furniture() {
    }

    public Furniture(String name, String description, Double material_cube_meter) {
        this.name = name;
        this.description = description;
        this.material_cube_meter = material_cube_meter;
    }

    public Furniture(Integer id, String name, String description, Double material_cube_meter) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.material_cube_meter = material_cube_meter;
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

    public Double getMaterial_cube_meter() {
        return material_cube_meter;
    }

    public void setMaterial_cube_meter(Double material_cube_meter) {
        this.material_cube_meter = material_cube_meter;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", material_cube_meter=" + material_cube_meter +
                '}';
    }
}


