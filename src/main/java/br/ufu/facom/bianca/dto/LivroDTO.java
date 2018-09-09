package br.ufu.facom.bianca.dto;

import java.io.Serializable;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer nro_paginas;
	
	public LivroDTO() {}

	public LivroDTO(Integer id, String nome, String descricao, Integer nro_paginas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.nro_paginas = nro_paginas;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNro_paginas() {
		return nro_paginas;
	}

	public void setNro_paginas(Integer nro_paginas) {
		this.nro_paginas = nro_paginas;
	}
	
}
