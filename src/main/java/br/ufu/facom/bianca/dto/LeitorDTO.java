package br.ufu.facom.bianca.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.ufu.facom.bianca.domain.Leitor;

public class LeitorDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	// Leitor para transferencia de dados 
	
	private Integer id;
	
	@NotEmpty(message= "Preenchimento obrigatorio")
	@Length(min=5,max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message= "Preenchimento obrigatorio")
	@Email(message="Email invalido")
	private String email; 
	
	public LeitorDTO() {}

	public LeitorDTO(Leitor obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}
