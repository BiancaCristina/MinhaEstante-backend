package br.ufu.facom.bianca.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufu.facom.bianca.domain.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer nro_paginas;
	private EditoraDTO editoraDTO; // Setar a editora quando instanciar (usar o metodo set)
	private List<AutorDTO> autoresDTO = new ArrayList<>();
	
	public LivroDTO() {}

	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
		this.nro_paginas = obj.getNro_paginas();
		this.editoraDTO = new EditoraDTO(obj.getEditora());
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

	public EditoraDTO getEditoraDTO() {
		return editoraDTO;
	}

	public void setEditoraDTO(EditoraDTO editoraDTO) {
		this.editoraDTO = editoraDTO;
	}

	public List<AutorDTO> getAutoresDTO() {
		return autoresDTO;
	}

	public void setAutoresDTO(List<AutorDTO> autoresDTO) {
		this.autoresDTO = autoresDTO;
	}
	
}
