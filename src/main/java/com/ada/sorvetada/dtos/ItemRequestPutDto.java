package com.ada.sorvetada.dtos;

import jakarta.validation.constraints.NotNull;

public record ItemRequestPutDto(
        // @NotNull(message = "O campo id do item é obrigatório!")
        Long id,

        @NotNull(message = "O campo id do produto é obrigatório!")
        Long productId,

        @NotNull(message = "O campo quantidade é obrigatório!")
        int quantity,

        @NotNull(message = "O campo preço é obrigatório!")
        double price
) {
        public ItemRequestPutDto(ItemRequestPostDto itemPost) {
                this(
                        null,
                        itemPost.productId(),
                        itemPost.quantity(),
                        itemPost.price()
                );
        }
}
