package org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.serratec.ecommerce.model.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
