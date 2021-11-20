package org.serratec.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.serratec.ecommerce.model.domain.Categoria;
import org.serratec.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Page<Categoria> listarCategoria(Pageable pageable) {
		Page<Categoria> categorias = categoriaRepository.findAll(pageable);
		return categorias;
	}
	
	public Categoria inserir(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria buscar(String nome) {
		Optional<Categoria> cate = categoriaRepository.findByNome(nome);
		if (cate.isPresent()) {
			return cate.get();
		}
		return null;
	}

	public Categoria atualizar(Long id, Categoria categoria) {
		Optional<Categoria> cat = categoriaRepository.findById(id);
		if (cat.isPresent()) {
			cat.get().setId(id);
			cat.get().setNome(categoria.getDescricao());
			cat.get().setDescricao(categoria.getDescricao());
		}
		return categoriaRepository.save(cat.get());
	}
	
	public boolean apagar(Long id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
