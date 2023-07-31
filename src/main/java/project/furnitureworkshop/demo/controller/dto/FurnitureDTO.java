package project.furnitureworkshop.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class FurnitureDTO implements Serializable {

    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Double material_cube_meter;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FurnitureDTO that = (FurnitureDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(material_cube_meter, that.material_cube_meter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, material_cube_meter);
    }

    @Override
    public String toString() {
        return "FurnitureDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", material_cube_meter=" + material_cube_meter +
                '}';
    }
}
