package br.ufu.facom.bianca.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.ufu.facom.bianca.domain.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Length(min=5,max=200,message="O tamanho deve estar entre 5 e 200 caracteres")
	private String nome;
	
	private String descricao;
	private Integer nroPaginas;
	private EditoraDTO editoraDTO;
	private List<AutorDTO> autoresDTO = new ArrayList<>();
	
	public LivroDTO() {}

	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
		this.nroPaginas = obj.getNroPaginas();
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

	public Integer getNroPaginas() {
		return nroPaginas;
	}

	public void setNroPaginas(Integer nroPaginas) {
		this.nroPaginas = nroPaginas;
	}
	
}
