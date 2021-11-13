package org.serratec.ecommerce.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente {

	@ApiModelProperty(value = "Identificador do cliente")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	
	@ApiModelProperty(value = " Campo para ser digitado o e-mail")
	@NotBlank(message = "Email não pode ser vazio")
	@Size(message = "Email não pode ser maior que 30", max = 30)
	@Column(length = 30)
	private String email;

	@ApiModelProperty(value = " Colocar o nome para identificar o usuario")
	@NotBlank(message = "Nome do usuário não pode ser vazio")
	@Size(message = "Nome do usuário não pode ser maior que 20", max = 20)
	@Column(name = "nome_usuario", length = 20)
	private String nomeUsuario;

	@ApiModelProperty(value = " Para colocar o nome completo")
	@NotBlank(message = "Nome completo do usuário não pode ser vazio")
	@Size(message = "Nome completo do usuário não pode ser maior que 60", max = 60)
	@Column(name = "nome_completo", length = 60)
	private String nomeCompleto;

	@ApiModelProperty(value = " Campo para digitar a senha do úsuario")
	private String senha;

	@ApiModelProperty(value = " Campo que vai ser digitado o cpf")
	@NotBlank(message = "Cpf não pode ser vazio")
	@Size(message = "Cpf não pode ser maior que 11", max = 11)
	@CPF(message = "Cpf deve ser válido")
	@Column(length = 11)
	private String cpf;

	@ApiModelProperty(value = " Colocar o telefone para entrar em contato")
	@Size(message = "Telefone não pode ser maior que 11", max = 11)
	@Column(length = 11)
	private String telefone;

	@ApiModelProperty(value = " Campo para colocar a data de nascimento do úsuario")
	@Past(message = "Data de nascimento deve ser no passado")
	@Column(name = "data_nasc")
	private LocalDate dataNasc;

	@ApiModelProperty(value = "Colocar o endereço do úsuario")
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@ApiModelProperty(value = " Para visualizar uma lista de pedidos para o cliente ")
	@JsonManagedReference
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public Cliente() {
	}

	public Cliente(Long id,
			@NotBlank(message = "Email não pode ser vazio") @Size(message = "Email não pode ser maior que 30", max = 30) String email,
			@NotBlank(message = "Nome do usuário não pode ser vazio") @Size(message = "Nome do usuário não pode ser maior que 20", max = 20) String nomeUsuario,
			@NotBlank(message = "Nome completo do usuário não pode ser vazio") @Size(message = "Nome completo do usuário não pode ser maior que 60", max = 60) String nomeCompleto,
			String senha,
			@NotBlank(message = "Cpf não pode ser vazio") @Size(message = "Cpf não pode ser maior que 14", max = 14) @CPF(message = "Cpf deve ser válido") String cpf,
			@Size(message = "Telefone não pode ser maior que 11", max = 11) String telefone,
			@Past(message = "Data de nascimento deve ser no passado") LocalDate dataNasc, Endereco endereco,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.email = email;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.pedidos = pedidos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

