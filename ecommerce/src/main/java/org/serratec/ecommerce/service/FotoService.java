package org.serratec.ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.serratec.ecommerce.model.domain.Foto;
import org.serratec.ecommerce.model.domain.Produto;
import org.serratec.ecommerce.repository.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;

	// MultpartFile - Permite receber arquivos
	public Foto inserir(Produto produto, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		foto.setNome(file.getName());
		foto.setProduto(produto);
		return fotoRepository.save(foto);
	}

	public Foto buscar(Long id) {
		Optional<Foto> foto = fotoRepository.findById(id);
		if (!foto.isPresent()) {
			return null;
		}
		return foto.get();
	}

}
