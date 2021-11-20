package org.serratec.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.serratec.ecommerce.model.domain.Endereco;
import org.serratec.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco inserir(Endereco endereco, Integer numero) {
		endereco.setNumero(numero);
		return enderecoRepository.save(endereco);
	}

	public Endereco buscar(String cep, Integer numero) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return endereco.get();
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserir(enderecoViaCep.get(), numero);
			} else {
				return null;
			}
		}
	}

}
