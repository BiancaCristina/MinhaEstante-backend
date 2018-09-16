package br.ufu.facom.bianca.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LeitorNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	// Leitor para insercao
	// Nao precisa do ID porque isso vai ser gerado pelo banco de dados
	@NotEmpty(message= "Preenchimento obrigatório")
	@Length(min=5,max=120,message="O tamanho deve estar entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	public LeitorNewDTO() {}

	public LeitorNewDTO(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
