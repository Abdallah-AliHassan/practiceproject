package com.abdallahsproject.Customer.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(
        name = "factory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "factory_name_unique",
                        columnNames = "name"
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class Factory {

    @Id
    @SequenceGenerator(
            name = "factory_id_seq",
            sequenceName = "factory_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "factory_id_seq"
    )
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarModel name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String location;

    @JsonManagedReference
    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL)
    private List<CarSpecs> carSpecs;

    public Factory(CarModel name, String country, String location, List<CarSpecs> carSpecs) {
        this.name = name;
        this.country = country;
        this.location = location;
        this.carSpecs = carSpecs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarModel getName() {
        return name;
    }

    public void setName(CarModel name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<CarSpecs> getCarSpecs() {
        return carSpecs;
    }

    public void setCarSpecs(List<CarSpecs> carSpecs) {
        this.carSpecs = carSpecs;
    }

    public Factory(CarModel name, String country, String location) {
        this.name = name;
        this.country = country;
        this.location = location;
    }
}
