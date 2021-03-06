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

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.dto.CategoriaDTO;
import br.ufu.facom.bianca.repositories.CategoriaRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;
import br.ufu.facom.bianca.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	@Transactional
	public Categoria insert (Categoria obj) {
		// Metodo que insere uma nova Categoria 
		obj.setId(null);

		return repo.save(obj);
	}	
	
	public Categoria update (Categoria obj) {
		// Metodo que atualiza a categoria
		
		Categoria newObj = this.find(obj.getId()); // Procura o obj do parametro pelo ID e faz "newObj" recebe-lo (caso exista)
		this.updateData(newObj,obj); // Salva os dados de newObj de acordo com os dados previos de obj
		return repo.save(newObj);
	}
	
	private void updateData(Categoria newObj,Categoria obj) {
		// Metodo exclusivo de CategoriaService que vai atualizar o nome da Categoria
		// So atualiza o nome e nada mais porque eh o unico atributo de CategoriaDTO que pode ser atualizado
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		// Metodo que deleta uma Categoria
		this.find(id);
		
		try 
		{			
			repo.deleteById(id);
		}
		
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui livros associados");
		}		
	}
	
	public List<Categoria> findAll(){
		// Esse metodo retorna todas as categorias
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// Esse metodo acha todas as categorias de forma paginada 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Page<Categoria> findPageByNome(String nome, Integer page, Integer linesPerPage, String orderBy, String direction){
		// Esse metodo faz a busca por nome de forma paginada
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return repo.findByNomeContainingIgnoreCase(nome,pageRequest);
	}
	
	public List<Categoria> findByNome(String nome){
		// Esse metodo encontra as editoras que possuam esse nome
		return repo.findByNomeContainingIgnoreCase(nome);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Categoria a partir de um objeto do tipo CategoriaDTO
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
}
