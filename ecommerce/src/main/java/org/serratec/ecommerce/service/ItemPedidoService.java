package org.serratec.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.serratec.ecommerce.exception.EstoqueException;
import org.serratec.ecommerce.model.domain.ItemPedido;
import org.serratec.ecommerce.model.domain.Produto;
import org.serratec.ecommerce.repository.ItemPedidoRepository;
import org.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<ItemPedido> listar(Pageable pageable) {
		return itemPedidoRepository.findAll(pageable);
	}
	
	public ItemPedido buscar(Long id) {
		Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
		if (itemPedido.isPresent()) {
			return itemPedido.get();
		}
		return null;
	}
	
	public ItemPedido inserir(ItemPedido itemPedido) throws EstoqueException {
		itemPedido.setPrecoVenda(calcularItemVenda(itemPedido));
		
		if (itemPedido.getQuantidade()> itemPedido.getProduto().getQtdEstoque()) {
			throw new EstoqueException("Não tem estoque suficiente");
		}
		itemPedido.getProduto().setQtdEstoque(itemPedido.getProduto().getQtdEstoque() - itemPedido.getQuantidade()); 
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido atualizar(Long id, ItemPedido itemPedido) {
		itemPedido.setPrecoVenda(calcularItemVenda(itemPedido));
		if (!itemPedidoRepository.existsById(id)) {
			return null;
		}
		itemPedido.setId(id);
		return itemPedidoRepository.save(itemPedido);
	}
	
	public boolean apagar(Long id) {
		if (!itemPedidoRepository.existsById(id)) {
			return false;
		}
		itemPedidoRepository.deleteById(id);
		return true;
	}
	
	public Double calcularItemVenda(ItemPedido itemPedido) {
		Produto produto = produtoRepository.findById( itemPedido.getProduto().getId() ).get();
		return produto.getValorUnitario() * itemPedido.getQuantidade();
		
	}
}
