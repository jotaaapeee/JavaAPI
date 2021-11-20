package org.serratec.ecommerce.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Produto {

	@ApiModelProperty(value = "campo para identificar o id do produto ")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@ApiModelProperty(value = "campo para identificar o nome do produto ")
	@NotBlank(message = "Nome não pode estar vazio")
	@Size(max = 30)
	private String nome;

	@ApiModelProperty(value = "campo para identificar os dados descritos do produto ")
	@Size(max = 100, message = "Pode conter até 100 caracteres")
	private String descricao;

	@ApiModelProperty(value = "campo para identificar os dados do estoque do produto ")
	@NotNull(message = "Quantidade não pode ser nula")
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;

	@ApiModelProperty(value = "campo para identificar os dados da data cadatro do produto ")
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@ApiModelProperty(value = "campo para identificar o valor unitario do produto ")
	@NotNull(message = "Valor não pode estar vazio")
	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@Column
	private String url;

	@ApiModelProperty(value = "chave estrangeira para endentificar o categoria relacionado ao produto")
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@OneToOne(mappedBy = "produto", orphanRemoval = true)
	private Foto foto;

	public Produto() {
	}

	public Produto(Long id, @NotBlank(message = "Nome não pode estar vazio") @Size(max = 30) String nome,
			@Size(max = 100, message = "Pode conter até 100 caracteres") String descricao,
			@NotNull(message = "Quantidade não pode ser nula") Integer qtdEstoque, LocalDate dataCadastro,
			@NotNull(message = "Valor não pode estar vazio") Double valorUnitario, String url, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.url = url;
		this.categoria = categoria;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

