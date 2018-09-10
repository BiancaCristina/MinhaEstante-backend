package br.ufu.facom.bianca.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String descricao;
	private Integer nro_paginas;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable (name="LIVRO_CATEGORIA",
				joinColumns = @JoinColumn(name="livro_id"),
				inverseJoinColumns= @JoinColumn(name="categoria_id")
	) // JoinTable = faz a terceira tabela pro mapeamento n:n
	private List<Categoria> categorias = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="editora_id")
	private Editora editora; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="LIVRO_AUTOR",
			   joinColumns = @JoinColumn(name="livro_id"),
			   inverseJoinColumns= @JoinColumn(name="autor_id")
	)
	private List<Autor> autores = new ArrayList<>(); 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="LIVROLIDO_LEITOR",
			   joinColumns = @JoinColumn(name="livro_id"),
			   inverseJoinColumns = @JoinColumn(name="leitor_id")
	)
	private List<Leitor> leitoresLeram = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="LIVRODESEJADO_LEITOR",
			   joinColumns = @JoinColumn(name="livro_id"),
			   inverseJoinColumns = @JoinColumn(name="leitor_id")
	)
	private List<Leitor> leitoresDesejam = new ArrayList<>();
	
	public Livro() {}

	public Livro(Integer id, String nome, String descricao, Integer nro_paginas, Editora editora) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.nro_paginas = nro_paginas;
		this.editora = editora;
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
	
	@JsonIgnore
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@JsonIgnore // Adicionado para retirar referencia ciclica entre Livro e Editora
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public List<Leitor> getLeitoresLeram() {
		return leitoresLeram;
	}

	public void setLeitoresLeram(List<Leitor> leitoresLeram) {
		this.leitoresLeram = leitoresLeram;
	}

	public List<Leitor> getLeitoresDesejam() {
		return leitoresDesejam;
	}

	public void setLeitoresDesejam(List<Leitor> leitoresDesejam) {
		this.leitoresDesejam = leitoresDesejam;
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
