package org.serratec.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.serratec.ecommerce.model.domain.Cliente;
import org.serratec.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository clienteRepository;
	
	public Page<Cliente> listar(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente buscar(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}
	
	public Cliente inserir(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return null;
		}
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}
	
	public boolean apagar(Long id) {
		if (!clienteRepository.existsById(id)) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}
	
}
