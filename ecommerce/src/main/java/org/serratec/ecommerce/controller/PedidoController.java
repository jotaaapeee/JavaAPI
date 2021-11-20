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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.ecommerce.exception.EstoqueException;
import org.serratec.ecommerce.exception.StatusFinalizadoException;
import org.serratec.ecommerce.model.domain.Pedido;
import org.serratec.ecommerce.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@ApiOperation(value = "Inserir um pedido")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Pedido inserido com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PostMapping
	public ResponseEntity<Object> inserir(@Valid @RequestBody Pedido pedido) {
		try {
			pedido = pedidoService.inserir(pedido);
			return ResponseEntity.ok(pedido);
		} catch (EstoqueException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Listar pedidos")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Pedido listado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@GetMapping
	public ResponseEntity<Page<Pedido>> listar(@PageableDefault Pageable pageable) {
		Page<Pedido> pedidos = pedidoService.listar(pageable);
		return ResponseEntity.ok(pedidos);
	}

	/* @ApiOperation(value = "Atualizar um pedido")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Pedido atualizado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PutMapping("{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
		try {
			if (pedidoService.atualizar(id, pedido) == null) {
				return ResponseEntity.notFound().build();
			}

			pedido = pedidoService.atualizar(id, pedido);
			return ResponseEntity.ok(pedido);

		} catch (StatusFinalizadoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}*/

	@ApiOperation(value = "Atualizar as datas de um pedido")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Pedido atualizado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PutMapping("/atualizarPedido/{id}")
	public ResponseEntity<Object> atualizarPedido(@PathVariable Long id, @RequestParam String dataEntrega)
			throws StatusFinalizadoException {
		try {
			return ResponseEntity.ok(pedidoService.atualizarPedido(id, dataEntrega));
		} catch (StatusFinalizadoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Deletar um pedido")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Pedido deletado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!pedidoService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}