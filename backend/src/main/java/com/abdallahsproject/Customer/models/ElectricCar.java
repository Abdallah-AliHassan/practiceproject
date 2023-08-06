package com.abdallahsproject.Customer.models;


import jakarta.persistence.*;

@Entity
@Table(name = "electriccar")
public class ElectricCar {

    @Id
    @SequenceGenerator(
            name = "electriccar_id_seq",
            sequenceName = "electriccar_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "electriccar_id_seq"
    )
    private Long id;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarModel model;

    public ElectricCar() {

    }

    public ElectricCar(String email, CarModel model) {
        this.email = email;
        this.model = model;
    }

    public ElectricCar(Long id, String email, CarModel model) {
        this.id = id;
        this.email = email;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }
}