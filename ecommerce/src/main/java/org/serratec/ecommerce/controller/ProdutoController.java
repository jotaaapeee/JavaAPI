package org.serratec.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.serratec.ecommerce.model.domain.Foto;
import org.serratec.ecommerce.model.domain.Produto;
import org.serratec.ecommerce.service.FotoService;
import org.serratec.ecommerce.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FotoService fotoService;

	@ApiOperation(value = "Listar todos os produtos")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Produto listado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@GetMapping
	public ResponseEntity<List<Produto>> listar() {
		List<Produto> produto = produtoService.listar();
		return ResponseEntity.ok(produto);
	}

	@ApiOperation(value = "Buscar um produto por nome")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Produto encontrado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@GetMapping("/nome")
	public ResponseEntity<Produto> buscarPorNome(@RequestParam(defaultValue = "") String nome) {
		Produto produtos = produtoService.buscarPorNome(nome);
		if (produtos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", foto.getTipo());
		headers.add("content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@Valid @RequestBody Produto produto) {
		return produtoService.inserir(produto);
	}

	@ApiOperation(value = "Inserir um produto")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Produto inserido com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PostMapping("/foto")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserirComFoto(@RequestParam MultipartFile file, @Valid @RequestPart Produto produto)
			throws IOException {
		return produtoService.inserirComFoto(produto, file);
	}

	@ApiOperation(value = "Atualizar um produto")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "produto atualizado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@PutMapping("{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		if (produtoService.atualizar(id, produto) != null) {
			return ResponseEntity.ok(produtoService.atualizar(id, produto));
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Deletar um produto")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Produto deletado com sucesso!"),
			@ApiResponse(code = 401, message = "Erro de autenticação"), @ApiResponse(code = 403, message = "Proibido"),
			@ApiResponse(code = 404, message = "Recurso não disponível"),
			@ApiResponse(code = 500, message = "Erro interno no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (produtoService.deletar(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

