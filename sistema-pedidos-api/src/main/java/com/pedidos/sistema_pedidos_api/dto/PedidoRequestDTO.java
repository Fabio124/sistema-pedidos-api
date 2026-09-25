package com.pedidos.sistema_pedidos_api.dto;

import java.util.List;

public class PedidoRequestDTO {

    private Long clienteId;
    private List<DetalleRequestDTO> detalles;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Long clienteId, List<DetalleRequestDTO> detalles) {
        this.clienteId = clienteId;
        this.detalles = detalles;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRequestDTO> detalles) {
        this.detalles = detalles;
    }
}