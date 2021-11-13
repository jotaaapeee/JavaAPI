package org.serratec.ecommerce.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ItemPedido {

	@ApiModelProperty(value = "campo para identificar o id do item pedido ")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
    private Long id;

	@ApiModelProperty(value = "campo para identificar a quantidade item pedido ")
    @NotNull(message = "A quantidade não pode ser nula")
    private Integer quantidade;

	@ApiModelProperty(value = "campo para identificar o preço venda do item pedido ")
    @Positive(message = "Preço da venda deve ser positivo")
    @Column(name = "preco_venda")
    private Double precoVenda; // Preço total do item na venda.
    
	@ApiModelProperty(value = "chave estrangeira para endentificar o pedido relacionado ao item pedido")
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

	@ApiModelProperty(value = "chave estrangeira para endentificar o produto relacionado ao item pedido")
	@ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
   	
   	public ItemPedido() {
	}

	public ItemPedido(Long id, @NotBlank(message = "A quantidade não pode ser nula") Integer quantidade,
			@Positive(message = "Preço da venda deve ser positivo") Double precoVenda, Pedido pedido, Produto produto) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.pedido = pedido;
		this.produto = produto;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   	
}

