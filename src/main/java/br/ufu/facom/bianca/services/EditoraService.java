package br.ufu.facom.bianca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.dto.EditoraDTO;
import br.ufu.facom.bianca.repositories.EditoraRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;
import br.ufu.facom.bianca.services.exceptions.DataIntegrityException;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repo;
	
	public Editora find (Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Editora> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Editora.class.getName()));
	}
	
	public Editora insert (Editora obj) {
		// Metodo que insere uma nova Editora 
		obj.setId(null);

		return repo.save(obj);
	}	
	
	public Editora update (Editora obj) {
		// Metodo que atualiza a categoria
		
		Editora newObj = this.find(obj.getId()); // Procura o obj do parametro pelo ID e faz "newObj" recebe-lo (caso exista)
		this.updateData(newObj,obj); // Salva os dados de newObj de acordo com os dados previos de obj
		return repo.save(newObj);
	}
	
	private void updateData(Editora newObj,Editora obj) {
		// Metodo exclusivo de EditoraService que vai atualizar o nome da Editora
		// So atualiza o nome e nada mais porque eh o unico atributo de EditoraDTO que pode ser atualizado
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		// Metodo que deleta uma Editora
		this.find(id);
		
		try 
		{			
			repo.deleteById(id);
		}
		
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir uma editora com livros/leitores associados");
		}		
	}
	
	public List<Editora> findAll(){
		// Esse metodo retorna todas as categorias
		return repo.findAll();
	}
	
	public Page<Editora> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// Esse metodo acha todas as categorias de forma paginada 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public List<Editora> findByNome(String nome){
		// Esse metodo encontra as editoras que possuam esse nome
		return repo.findByNomeContainingIgnoreCase(nome);
	}
	
	public Editora fromDTO(EditoraDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Editora a partir de um objeto do tipo EditoraDTO
		return new Editora(objDTO.getId(),objDTO.getNome());
	}
	
	public EditoraDTO toDTO (Editora obj) {
		// Metodo auxiliar que instancia um objeto do tipo EditoraDTO a partir de um objeto do tipo Editora
		return new EditoraDTO(obj);
	}
}
