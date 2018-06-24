package com.romantupikov.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Company extends AbstractEntity {

    @Size(max = 255, min = 3, message = "{error.size}")
    @NotEmpty(message="{error.empty}")
    private String name;

    @NotEmpty(message="{error.empty}")
    private String description;

    @NotEmpty(message="{error.empty}")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(description, company.description) &&
                Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, address);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
