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

import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.dto.LeitorDTO;
import br.ufu.facom.bianca.dto.LeitorNewDTO;
import br.ufu.facom.bianca.repositories.LeitorRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;
import br.ufu.facom.bianca.services.exceptions.DataIntegrityException;

@Service
public class LeitorService {
	
	@Autowired
	private LeitorRepository repo;
	
	public Leitor find(Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Leitor> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Leitor.class.getName()));
	}
	
	@Transactional
	public Leitor insert (Leitor obj) {
		// Metodo que insere uma nova Leitor 
		obj.setId(null);

		return repo.save(obj);
	}
	
	public Leitor update (Leitor obj) {
		// Metodo que atualiza a categoria
		
		Leitor newObj = this.find(obj.getId()); // Procura o obj do parametro pelo ID e faz "newObj" recebe-lo (caso exista)
		this.updateData(newObj,obj); // Salva os dados de newObj de acordo com os dados previos de obj
		return repo.save(newObj);
	}
	
	private void updateData(Leitor newObj,Leitor obj) {
		// Metodo exclusivo de LeitorService que vai atualizar o nome da Leitor
		// Atualiza nome, email e senha
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		// Metodo que deleta uma Leitor
		this.find(id);
		
		try 
		{			
			repo.deleteById(id);
		}
		
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possível excluir um leitor com livros/editoras/autores associados.");
		}		
	}
	
	public List<Leitor> findAll(){
		// Esse metodo retorna todas as categorias
		return repo.findAll();
	}
	
	public Page<Leitor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		// Esse metodo acha todas as categorias de forma paginada 
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Leitor fromDTO(LeitorDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Leitor a partir de um objeto do tipo LeitorDTO
		return new Leitor(objDTO.getId(),objDTO.getNome(), objDTO.getEmail(),null);
	}
	
	public Leitor fromDTO(LeitorNewDTO objDTO) {
		// Metodo para converter LeitorNewDTO em Leitor
		Leitor leitor = new Leitor(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getSenha());
		
		return leitor;
	}
	
	public LeitorDTO toDTO (Leitor obj) {
		// Metodo auxiliar que instancia um objeto do tipo LeitorDTO a partir de um objeto do tipo Leitor
		return new LeitorDTO(obj);
	}
}
