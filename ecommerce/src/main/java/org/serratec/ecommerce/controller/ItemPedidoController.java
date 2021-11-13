package org.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.ecommerce.exception.EstoqueException;
import org.serratec.ecommerce.model.domain.ItemPedido;
import org.serratec.ecommerce.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itensPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@ApiOperation(value = "Listar itens do pedido")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "itens do pedido listados com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@GetMapping
	public ResponseEntity<Page<ItemPedido>> listar(Pageable pageable) {
		Page<ItemPedido> itensPedido = itemPedidoService.listar(pageable);
		return ResponseEntity.ok(itensPedido);
	}

	@ApiOperation(value = "Inserir um item pedido")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Item do pedido inserido com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@PostMapping
	public ResponseEntity<ItemPedido> inserir(@RequestBody ItemPedido itemPedido) throws EstoqueException{
		itemPedido = itemPedidoService.inserir(itemPedido);
		return ResponseEntity.ok(itemPedido);
	}

	@ApiOperation(value = "Atualizar um pedido item ")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Item do pedido atualizado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@PutMapping("{id}")
	public ResponseEntity<ItemPedido> atualizar(@RequestBody ItemPedido itemPedido, @PathVariable Long id) {
		itemPedido = itemPedidoService.atualizar(id, itemPedido);
		if (itemPedido == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(itemPedido);
	}

}
