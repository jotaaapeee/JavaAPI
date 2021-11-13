package org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.serratec.ecommerce.model.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
