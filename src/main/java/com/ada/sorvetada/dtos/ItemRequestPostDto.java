package com.ada.sorvetada.dtos;


import javax.validation.constraints.NotNull;

public record ItemRequestPostDto(
        @NotNull(message = "O campo id do produto é obrigatório!")
        Long productId,

        @NotNull(message = "O campo quantidade é obrigatório!")
        int quantity,

        @NotNull(message = "O campo preço é obrigatório!")
        double price
) {
}
