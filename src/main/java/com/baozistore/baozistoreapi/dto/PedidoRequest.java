package com.baozistore.baozistoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    private Long clienteId;
    private Long produtoId;
    private Integer quantidade;
}