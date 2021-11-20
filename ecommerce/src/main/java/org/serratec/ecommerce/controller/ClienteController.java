package org.serratec.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.ecommerce.model.domain.Cliente;
import org.serratec.ecommerce.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "listar todos os clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "clientes listados com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@GetMapping
	public ResponseEntity<Page<Cliente>> listar(@PageableDefault Pageable pageable) {
		Page<Cliente> clientes = clienteService.listar(pageable);
		return ResponseEntity.ok(clientes);
	}

	@ApiOperation(value = "buscar um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "cliente encontrado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@GetMapping("{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		if (clienteService.buscar(id) != null) {
			return ResponseEntity.ok(clienteService.buscar(id));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "inserir um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "cliente inserido com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
		cliente = clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}

	@ApiOperation(value = "atualizar um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "cliente atualizado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		if (clienteService.atualizar(id, cliente) != null) {
			return ResponseEntity.ok(clienteService.atualizar(id, cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Deletar um cliente")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "cliente deletado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (clienteService.apagar(id)) {
			clienteService.apagar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
