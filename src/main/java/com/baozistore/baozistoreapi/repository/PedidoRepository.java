package com.baozistore.baozistoreapi.repository;

import com.baozistore.baozistoreapi.entity.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
