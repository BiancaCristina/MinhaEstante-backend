package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Autor;
import br.ufu.facom.bianca.dto.AutorDTO;
import br.ufu.facom.bianca.repositories.AutorRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;

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
	
	public Autor insert (Autor obj) {
		// Metodo que insere uma nova Autor 
		obj.setId(null);

		return repo.save(obj);
	}
	
	public Autor fromDTO(AutorDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Autor a partir de um objeto do tipo AutorDTO
		return new Autor(objDTO.getId(),objDTO.getNome());
	}
}
