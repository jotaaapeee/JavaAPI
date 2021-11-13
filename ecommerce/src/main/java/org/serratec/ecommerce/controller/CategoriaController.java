package org.serratec.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.ecommerce.model.domain.Categoria;
import org.serratec.ecommerce.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


// Dentro do endereço http://localhost:8080/api/categoria
// terei todos os métodos disponíveis (GET, POST,PUT, etc...)
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@ApiOperation(value = "listar todas as categorias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categorias listadas com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@GetMapping
	public ResponseEntity<Page<Categoria>> listar(@PageableDefault Pageable pageable) {
		Page<Categoria> categorias = categoriaService.listarCategoria(pageable);
		return ResponseEntity.ok(categorias);

	}

	@ApiOperation(value = "Buscar uma categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria encontrada com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@GetMapping("/nome")
	public ResponseEntity<Categoria> buscarNome(@RequestParam(defaultValue = "") String nome) {
		Categoria categoria = categoriaService.buscar(nome);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Atualizar uma categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria atualizada com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
            
	@PutMapping("{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		if (categoriaService.atualizar(id, categoria) != null) {
			return ResponseEntity.ok(categoriaService.atualizar(id, categoria));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Inserir uma categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria inserida com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return categoriaService.inserir(categoria);
	}

	@ApiOperation(value = "Deletar uma categoria")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Categoria apagada com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })

	@DeleteMapping("{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (categoriaService.apagar(id)) {
			categoriaService.apagar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

