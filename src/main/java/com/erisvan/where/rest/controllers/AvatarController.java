
package com.erisvan.where.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.rest.dto.AtualizacaoStatusAvatarDTO;
import com.erisvan.where.rest.dto.InformacaoItemAvatarDTO;
import com.erisvan.where.rest.dto.InformacoesAvatarDTO;
import com.erisvan.where.rest.dto.AvatarDTO;
import com.erisvan.where.service.AvatarService_;

@RestController
@RequestMapping("/api/pedidos")
public class AvatarController {

    @Autowired
    private AvatarService_ service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody AvatarDTO dto) {
        Avatar pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesAvatarDTO getById(@PathVariable Integer id) {
        return service
                .obterAvatarCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar não encontrado."));
    }

    private InformacoesAvatarDTO converter(Avatar pedido) {
        return InformacoesAvatarDTO
                .builder()
                .codigo(pedido.getId())
                .dataAvatar(pedido.getDataAvatar().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemAvatarDTO> converter(List<ItemAvatar> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemAvatarDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
            @RequestBody AtualizacaoStatusAvatarDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, CallingStatus.valueOf(novoStatus));
    }
}
