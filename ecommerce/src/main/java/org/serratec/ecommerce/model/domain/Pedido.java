package org.serratec.ecommerce.model.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {

	@ApiModelProperty(value = "campo para identificar o id do pedido")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;

	@ApiModelProperty(value = "campo para identificar a data pedido do pedido")
	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@ApiModelProperty(value = "campo para identificar a data entrega do pedido")
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@ApiModelProperty(value = "campo para identificar a data envio do pedido")
	@Column(name = "data_envio")
	private LocalDate dataEnvio;

	@ApiModelProperty(value = "o enum para identificar o status do pedido")
	@Enumerated(EnumType.STRING)
	private Status status;

	@ApiModelProperty(value = "chave estrangeira para referenciar o pedido relacionado ao cliente")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ApiModelProperty(value = "chave estrangeira para referenciar uma lista de itens pedidos ao pedido")
	@JsonManagedReference							//era merge até o dia ----> 25/06
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // Colocamos porque sem o PERSIST dava ERRO 415
	private List<ItemPedido> itensPedido;

	public Pedido() {
	}

	public Pedido(Long id, @NotNull(message = "Data pedido não deve estar vazia") LocalDate dataPedido,
			LocalDate dataEntrega, LocalDate dataEnvio, Status status, Cliente cliente, List<ItemPedido> itensPedido) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.cliente = cliente;
		this.itensPedido = itensPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Double calcularValorTotal(List<ItemPedido> itensPedido) {
		Double total = 0.00;

		for (ItemPedido itemPedido1 : itensPedido) {
			total += itemPedido1.getPrecoVenda();
		}
		return total;
	}

	@Override
	public String toString() {

		String volta = "Data de Envio: " + dataEnvio + "\nData de Entrega: " + dataEntrega;

		for (ItemPedido itemPedido : itensPedido) {
			volta += "\nProduto - " + itemPedido.getProduto().getNome() + " - " + itemPedido.getQuantidade();
		}

		volta += "\n\n\nPreço Total: " + calcularValorTotal(itensPedido);

		return volta;

	}

}
