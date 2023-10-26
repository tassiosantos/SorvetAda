package com.ada.sorvetada.dtos;

import com.ada.sorvetada.entities.Order;

import java.util.List;

public record OrderResponseDto(
        Long idOrder,

        Long customerId,

        double totalPrice,

        List<ItemResponseDto> items
) {
    public OrderResponseDto(Order order) {
        this(
                order.getId(),
                order.getCustomer().getId(),
                order.getTotalPrice(),
                order.getItems().stream().map(ItemResponseDto::new).toList()
        );
    }
}
