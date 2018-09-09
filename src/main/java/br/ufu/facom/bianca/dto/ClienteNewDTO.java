package br.ufu.facom.bianca.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	// Leitor para insercao
	// Nao precisa do ID porque isso vai ser gerado pelo banco de dados
	
	private String nome;
	private String email;
	private String senha;
	
	public ClienteNewDTO() {}

	public ClienteNewDTO(String nome, String email, String senha) {
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
