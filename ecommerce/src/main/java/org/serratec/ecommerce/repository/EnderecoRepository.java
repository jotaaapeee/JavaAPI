package org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.serratec.ecommerce.model.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public Endereco findByCep(String cep);
}
