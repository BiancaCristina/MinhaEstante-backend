package br.ufu.facom.bianca.dto;

import java.io.Serializable;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer nome; 
	
	public AutorDTO() {}

	public AutorDTO(Integer id, Integer nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNome() {
		return nome;
	}

	public void setNome(Integer nome) {
		this.nome = nome;
	}
	
}
