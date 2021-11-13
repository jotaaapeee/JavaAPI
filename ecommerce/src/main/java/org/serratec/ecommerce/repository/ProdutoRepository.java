package org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.serratec.ecommerce.model.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Produto findByNome(String nome);
}
