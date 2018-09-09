package br.ufu.facom.bianca.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Leitor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@ManyToMany(mappedBy="leitoresDesejam")
	private List<Livro> livrosDesejados = new ArrayList<>();
	
	@ManyToMany(mappedBy="leitoresLeram")
	private List<Livro> livrosLidos = new ArrayList<>();
	
	@ManyToMany(mappedBy="leitoresGostam")
	private List<Autor> autoresFavoritos = new ArrayList<>();
	
	public Leitor() {}

	public Leitor(Integer id, String nome) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivrosDesejados() {
		return livrosDesejados;
	}

	public void setLivrosDesejados(List<Livro> livrosDesejados) {
		this.livrosDesejados = livrosDesejados;
	}

	public List<Livro> getLivrosLidos() {
		return livrosLidos;
	}

	public void setLivrosLidos(List<Livro> livrosLidos) {
		this.livrosLidos = livrosLidos;
	}
	
	public List<Autor> getAutoresFavoritos() {
		return autoresFavoritos;
	}

	public void setAutoresFavoritos(List<Autor> autoresFavoritos) {
		this.autoresFavoritos = autoresFavoritos;
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
		Leitor other = (Leitor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
