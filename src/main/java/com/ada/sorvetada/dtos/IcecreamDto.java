package com.ada.sorvetada.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class IcecreamDto {
    private long id;
    private String name;
    private Double unitPrice;
    private int availableUnits;
    private String urlPhoto;
}
