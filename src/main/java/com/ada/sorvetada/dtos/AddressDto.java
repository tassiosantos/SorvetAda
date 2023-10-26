package com.ada.sorvetada.dtos;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    private Long customerId;

}
