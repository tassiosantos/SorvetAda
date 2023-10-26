package com.ada.sorvetada.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestPostDto(
        @NotNull(message = "O campo id do usuário é obrigatório!")
        Long customerId,

        @NotNull(message = "O campo preço total é obrigatório!")
        double totalPrice,

        @NotNull(message = "O campo lista de itens é obrigatório!")
        List<ItemRequestPostDto> items
) {
}
