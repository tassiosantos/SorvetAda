
package com.ada.sorvetada.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private double totalPrice;


    @OneToMany(
            // fetch = FetchType.LAZY,
            // fetch = FetchType.EAGER,
            mappedBy = "order",
            /*cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
            cascade = CascadeType.ALL/*,
            orphanRemoval = true*/
    )
    private List<Item> items;
}

