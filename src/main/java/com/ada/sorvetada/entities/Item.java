package com.ada.sorvetada.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(of = {"id", "order", "product", "quantity"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            // fetch = FetchType.LAZY,
            // cascade = CascadeType.PERSIST,
            // cascade = CascadeType.ALL,
            // optional = false,
            targetEntity = Order.class
    )
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(optional = false)
    private Icecream product;

    @Column(nullable = false)
    private int quantity;

    private double price;

    public double getTotalItem() {
        return quantity * price;
    }
}
