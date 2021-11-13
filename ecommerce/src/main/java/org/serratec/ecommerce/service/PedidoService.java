package org.serratec.ecommerce.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.serratec.ecommerce.config.MailConfig;
import org.serratec.ecommerce.exception.EstoqueException;
import org.serratec.ecommerce.exception.StatusFinalizadoException;
import org.serratec.ecommerce.model.domain.Pedido;
import org.serratec.ecommerce.model.domain.Status;
import org.serratec.ecommerce.repository.ItemPedidoRepository;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository pedidoRepository;

	@Autowired
	public ProdutoRepository produtoRepository;

	@Autowired
	public ItemPedidoRepository itemPedidoRepository;

	@Autowired
	public ItemPedidoService itemPedidoService;
	
	@Autowired
	public MailConfig mailConfig;

	public Page<Pedido> listar(Pageable pageable) {
		Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
		return pedidos;
	}

	public Pedido inserir(Pedido pedido) throws EstoqueException {
		pedido.setStatus(Status.PROCESSANDO);
		pedido.setDataPedido(LocalDate.now());
		
		return pedidoRepository.save(pedido);
	}

	/*public Pedido atualizar(Long id, Pedido pedido) throws StatusFinalizadoException {
		Optional<Pedido> p = pedidoRepository.findById(id);
		
		if (p.get().getStatus() == Status.FINALIZADO) {
			throw new StatusFinalizadoException("Status não pode ser 'FINALIZADO'");
		}
		
		//System.out.println("Teste :" + pedido.getItensPedido().get(0).getProduto().getNome());
		
		if (p.isPresent()) {
			p.get().setId(id);
			//p.get().setItensPedido(pedido.getItensPedido());
			
			return pedidoRepository.save(p.get());
		}
		return null;
	}*/

	public Pedido atualizarPedido(Long id, String dataEntrega) throws StatusFinalizadoException{
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.get().getStatus() == Status.FINALIZADO) {
			throw new StatusFinalizadoException("Status não pode ser 'FINALIZADO'");
		}
		if (pedido.isPresent()) {
			pedido.get().setId(id);
			pedido.get().setDataEnvio(LocalDate.now());
			pedido.get().setStatus(Status.FINALIZADO);
			pedido.get().setDataEntrega(LocalDate.parse(dataEntrega));
			mailConfig.enviarEmail(pedido.get().getCliente().getEmail() , "Pedido finalizado !!", pedido.toString());
			return pedidoRepository.save(pedido.get()); 
		}
		return null;
	}
	
	public Boolean deletar(Long id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}