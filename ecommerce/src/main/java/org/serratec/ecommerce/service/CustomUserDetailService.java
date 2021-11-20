package org.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.ecommerce.model.domain.Cliente;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
	
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Cliente cliente = clienterepository.findByEmail(email);
		
		List<GrantedAuthority> autorizacoes = new ArrayList<>();
		
		autorizacoes.add(new SimpleGrantedAuthority("ROLE_Cliente"));
		
		return new User(
				cliente.getEmail(),
				cliente.getSenha(),
				autorizacoes
				);
	}
	
	
	
	

}
