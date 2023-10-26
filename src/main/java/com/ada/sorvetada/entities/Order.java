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

    /*@ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
            // cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "orders_items",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "item_id",
                    referencedColumnName = "id"
            )
    )*/
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
