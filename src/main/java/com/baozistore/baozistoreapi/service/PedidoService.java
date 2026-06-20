package com.baozistore.baozistoreapi.service;

import com.baozistore.baozistoreapi.entity.Cliente;
import com.baozistore.baozistoreapi.entity.Pedido;
import com.baozistore.baozistoreapi.entity.Produto;
import com.baozistore.baozistoreapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado com id: " + id));
    }

    public Pedido criar(Long clienteId, Long produtoId, Integer quantidade) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Produto produto = produtoService.buscarPorId(produtoId);

        validarEstoque(produto);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(quantidade);

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, Long clienteId, Long produtoId, Integer quantidade) {
        Pedido pedido = buscarPorId(id);

        Cliente cliente = clienteService.buscarPorId(clienteId);
        Produto produto = produtoService.buscarPorId(produtoId);

        validarEstoque(produto);

        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(quantidade);

        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }

    private void validarEstoque(Produto produto) {
        if (Boolean.FALSE.equals(produto.getEstoque())) {
            throw new IllegalStateException(
                    "Produto '" + produto.getNome() + "' esta sem estoque no momento."
            );
        }
    }
}