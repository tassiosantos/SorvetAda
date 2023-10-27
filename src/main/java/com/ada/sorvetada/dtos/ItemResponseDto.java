package com.ada.sorvetada.dtos;

import com.ada.sorvetada.entities.Item;


public record ItemResponseDto(
        Long idItem,

        Long productId,

        int quantity,

        double price
) {
        public ItemResponseDto(
                Item item
        ) {
                this(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getPrice()
                );
        }
}
