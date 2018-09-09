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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@ManyToMany(mappedBy="autores")
	private List<Livro> livrosPublicados = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="AUTOR_LEITOR",
				joinColumns = @JoinColumn(name="autor_id"),
				inverseJoinColumns = @JoinColumn(name="leitor_id")
	)
	private List<Leitor> leitoresGostam = new ArrayList<>();
	
	public Autor() {}
	
	public Autor(Integer id, String nome) {
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

	public List<Livro> getLivrosPublicados() {
		return livrosPublicados;
	}

	public void setLivrosPublicados(List<Livro> livrosPublicados) {
		this.livrosPublicados = livrosPublicados;
	}
	
	public List<Leitor> getLeitoresGostam() {
		return leitoresGostam;
	}

	public void setLeitoresGostam(List<Leitor> leitoresGostam) {
		this.leitoresGostam = leitoresGostam;
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
