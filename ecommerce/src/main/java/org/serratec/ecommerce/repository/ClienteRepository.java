package org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.serratec.ecommerce.model.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Cliente findByEmail(String email);
}
