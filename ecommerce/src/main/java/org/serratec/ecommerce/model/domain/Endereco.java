package org.serratec.ecommerce.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Endereco {
	@ApiModelProperty(value = "Identificador único de endereço")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	@ApiModelProperty(value = "Campo para digitar o cep da entrega")
	@Size(message = "Cep deve conter 9 digitos", max = 9)
	private String cep;

	@ApiModelProperty(value = "Campo para identificar o logradouro do usuário")
	@NotBlank(message = "Rua não pode ser vazio")
	@Size(message = "Rua pode conter no max 100 caracteres!", max = 100)
	@Column(length = 100, name = "rua")
	private String logradouro;

	@ApiModelProperty(value = "Campo para identificar o bairro do usuário ")
	@NotBlank(message = "Bairro não pode ser vazio")
	@Size(message = "Bairro pode conter até no máximo 50 caracteres", max = 50)
	@Column(length = 50)
	private String bairro;

	@ApiModelProperty(value = "Campo para identificar a localidade do usuário ")
	@Size(message = "Cidade pode conter até no máximo 30 caracteres", max = 30)
	@Column(length = 30, name = "cidade")
	private String localidade;

	@ApiModelProperty(value = "Campo para identificar oo numero do endereço do usuário ")
	@NotNull(message = "Número não pode ser vazio")
	private Integer numero;

	@ApiModelProperty(value = "Campo para identificar o complemento do endereço do usuário ")
	@Size(message = "Complemento pode conter até no máximo 20 caracteres", max = 20)
	@Column(length = 20)
	private String complemento;

	@ApiModelProperty(value = "Campo para identificar o estado do usuário ")
	@Size(message = "Estado pode conter até no máximo 2 caracteres", max = 2)
	@Column(length = 2, name = "estado")
	private String uf;

	public Endereco() {
	}

	public Endereco(Long id, @Size(message = "Cep deve conter 9 digitos", max = 9) String cep,
			@NotBlank(message = "Rua não pode ser vazio") @Size(message = "Rua pode conter no max 100 caracteres!", max = 100) String logradouro,
			@NotBlank(message = "Bairro não pode ser vazio") @Size(message = "Bairro pode conter até no máximo 50 caracteres", max = 50) String bairro,
			@Size(message = "Cidade pode conter até no máximo 30 caracteres", max = 30) String localidade,
			@NotNull(message = "Número não pode ser vazio") Integer numero,
			@Size(message = "Complemento pode conter até no máximo 20 caracteres", max = 20) String complemento,
			@Size(message = "Estado pode conter até no máximo 2 caracteres", max = 2) String uf) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

