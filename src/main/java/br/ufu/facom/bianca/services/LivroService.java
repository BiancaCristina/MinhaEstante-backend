package br.ufu.facom.bianca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Autor;
import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.domain.Livro;
import br.ufu.facom.bianca.dto.LivroDTO;
import br.ufu.facom.bianca.repositories.LivroRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;
import br.ufu.facom.bianca.services.exceptions.DataIntegrityException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repo;
	
	@Autowired
	private EditoraService editoraService;
	
	public Livro find(Integer id) {
		// Esse metodo busca um livro a partir de seu ID e o retorna como DTO (pra poder mostrar os autores)
		
		Optional<Livro> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
	}
	
	public Livro insert (Livro obj) {
		// Metodo que insere uma nova Livro 
		obj.setId(null);

		return repo.save(obj);
	}
	
	public Livro update (Livro obj) {
		// Metodo que atualiza a categoria
		
		Livro newObj = this.find(obj.getId()); // Procura o obj do parametro pelo ID e faz "newObj" recebe-lo (caso exista)
		this.updateData(newObj,obj); // Salva os dados de newObj de acordo com os dados previos de obj
		return repo.save(newObj);
	}
	
	private void updateData(Livro newObj,Livro obj) {
		// Metodo exclusivo de LivroService que vai atualizar o nome da Livro
		// Atualiza o nome, descricao, numero de paginas e editora
		// Nao modifica os autores 
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		newObj.setNroPaginas(obj.getNroPaginas());
		
		Integer id = obj.getEditora().getId(); // Pega o ID da editora
		
		editoraService.find(id); // Verifica se a editora existe
		
		if (id == null) {
			throw new ObjectNotFoundException("Editora nao encontrada! Id: " + id + ", Tipo: " + Editora.class.getName());
		}
		
		else {
			newObj.setEditora(obj.getEditora()); 
		}
	}
	
	public void delete(Integer id) {
		// Metodo que deleta uma Livro
		this.find(id);
		
		try 
		{			
			repo.deleteById(id);
		}
		
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Erro ao excluir livro!");
		}		
	}
	
	public List<Livro> findByNome(String nome){
		// Esse metodo encontra as editoras que possuam esse nome
		return repo.findByNomeContainingIgnoreCase(nome);
	}
	
	public Livro fromDTO(LivroDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Livro a partir de um objeto do tipo LivroDTO
		Livro newObj = new Livro(objDTO.getId(),
				objDTO.getNome(),
				objDTO.getDescricao(),
				objDTO.getNroPaginas(),
				editoraService.fromDTO(objDTO.getEditoraDTO()));
		
		// O comando abaixo seta os autores pro newObj a partir dos autoresDTO de objDTO
		newObj.setAutores(objDTO.getAutoresDTO().stream().map(obj -> new Autor(obj.getId(),obj.getNome())).collect(Collectors.toList()));
		
		return newObj;
		
	}
}
