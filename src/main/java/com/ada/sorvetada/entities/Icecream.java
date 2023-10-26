package com.ada.sorvetada.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Icecream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private int availableUnits;

    @Column(nullable = false)
    private String urlPhoto;

    public Icecream (String name, double unitPrice, int availableUnits, String urlPhoto){
        this.name = name;
        this.unitPrice = unitPrice;
        this.availableUnits = availableUnits;
        this.urlPhoto = urlPhoto;
    }
}
