package br.ufu.facom.bianca.dto;

import java.io.Serializable;

import br.ufu.facom.bianca.domain.Autor;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome; 
	
	public AutorDTO() {}

	public AutorDTO(Autor obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
	
}
