package com.erisvan.where.service;

import java.util.Optional;

import com.erisvan.where.enums.CallingStatus;
import com.erisvan.where.model.Pedido;
import com.erisvan.where.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, CallingStatus callingStatus);

}
