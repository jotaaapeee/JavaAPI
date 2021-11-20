package org.serratec.ecommerce.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Foto {

	@ApiModelProperty(value = "campo para identificar o id da foto do produto ")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto")
	private Long id;
	
	@ApiModelProperty(value = "campo para identificar os dados da foto do produto ")
	@Lob
	private byte[] dados;
	
	@ApiModelProperty(value = "campo para identificar o tipo da foto do produto ")
	private String tipo;
	
	@ApiModelProperty(value = "campo para identificar o nome da foto do produto  ")
	private String nome;
	
	@ApiModelProperty(value = "chave estrangeira para identificar o produto relacionado a foto")
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public Foto() {
	}

	public Foto(Long id, byte[] dados, String tipo, String nome, Produto produto) {
		super();
		this.id = id;
		this.dados = dados;
		this.tipo = tipo;
		this.nome = nome;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Foto other = (Foto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
