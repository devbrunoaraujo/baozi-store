package com.baozistore.baozistoreapi.controller;

import com.baozistore.baozistoreapi.dto.PedidoRequest;
import com.baozistore.baozistoreapi.entity.Pedido;
import com.baozistore.baozistoreapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoRequest request) {
        Pedido novoPedido = pedidoService.criar(
                request.getClienteId(),
                request.getProdutoId(),
                request.getQuantidade()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody PedidoRequest request) {
        Pedido pedidoAtualizado = pedidoService.atualizar(
                id,
                request.getClienteId(),
                request.getProdutoId(),
                request.getQuantidade()
        );
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}