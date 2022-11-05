package com.erisvan.where.service;

import java.util.Optional;

import com.erisvan.where.enums.StatusPedido;
import com.erisvan.where.model.Pedido;
import com.erisvan.where.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
