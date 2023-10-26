package com.ada.sorvetada.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestPutDto(
        @NotNull(message = "O campo preço total é obrigatório!")
        double totalPrice,

        @NotNull(message = "O campo lista de itens é obrigatório!")
        @Valid
        List<ItemRequestPutDto> items
) {
}
