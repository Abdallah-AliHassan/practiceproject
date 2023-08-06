package com.abdallahsproject.Customer.models;


import com.abdallahsproject.exception.DuplicateResourceException;
import com.abdallahsproject.exception.RequestValidationException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carspecs")
@NoArgsConstructor
@AllArgsConstructor
public class CarSpecs {

    @Id
    @SequenceGenerator(
            name = "carspecs_id_seq",
            sequenceName = "carspecs_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "carspecs_id_seq"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="factory_id", nullable = false, foreignKey=@ForeignKey(name="factory_id", value = ConstraintMode.CONSTRAINT))
    private Factory factory;

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public CarSpecs(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if(year > 2000 && year < 2024) {
            this.year = year;
        }
        else throw new RequestValidationException("Year is out of the supported range");
    }
}