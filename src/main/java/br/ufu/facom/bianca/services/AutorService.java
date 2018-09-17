package br.ufu.facom.bianca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufu.facom.bianca.domain.Autor;
import br.ufu.facom.bianca.dto.AutorDTO;
import br.ufu.facom.bianca.repositories.AutorRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;
import br.ufu.facom.bianca.services.exceptions.DataIntegrityException;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repo;
	
	public Autor find(Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Autor> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));
	}
	
	@Transactional
	public Autor insert (Autor obj) {
		// Metodo que insere uma nova Autor 
		obj.setId(null);

		return repo.save(obj);
	}
	
	public Autor update (Autor obj) {
		// Metodo que atualiza a categoria
		
		Autor newObj = this.find(obj.getId()); // Procura o obj do parametro pelo ID e faz "newObj" recebe-lo (caso exista)
		this.updateData(newObj,obj); // Salva os dados de newObj de acordo com os dados previos de obj
		return repo.save(newObj);
	}
	
	private void updateData(Autor newObj,Autor obj) {
		// Metodo exclusivo de AutorService que vai atualizar o nome da Autor
		// So atualiza o nome e nada mais porque eh o unico atributo de AutorDTO que pode ser atualizado
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		// Metodo que deleta uma Autor
		this.find(id);
		
		try 
		{			
			repo.deleteById(id);
		}
		
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir um autor com livros/leitores associados");
		}		
	}
	
	public List<Autor> findAll(){
		// Esse metodo retorna todas as categorias
		return repo.findAll();
	}
	
	public Page<Autor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// Esse metodo acha todas as categorias de forma paginada 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public List<Autor> findByNome(String nome){
		return repo.findByNomeContainingIgnoreCase(nome); 
	}
	public Autor fromDTO(AutorDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Autor a partir de um objeto do tipo AutorDTO
		return new Autor(objDTO.getId(),objDTO.getNome());
	}
	
}
