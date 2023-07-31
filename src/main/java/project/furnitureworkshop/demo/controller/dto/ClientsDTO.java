package project.furnitureworkshop.demo.controller.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class ClientsDTO {

    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @Digits(integer = 20, fraction = 0)
    private String phone;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsDTO clientsDTO = (ClientsDTO) o;
        return Objects.equals(id, clientsDTO.id) && Objects.equals(name, clientsDTO.name) && Objects.equals(surname, clientsDTO.surname) && Objects.equals(email, clientsDTO.email) && Objects.equals(phone, clientsDTO.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phone);
    }

    @Override
    public String toString() {
        return "ClientsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
