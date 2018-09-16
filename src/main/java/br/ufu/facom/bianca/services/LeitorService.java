package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.dto.LeitorDTO;
import br.ufu.facom.bianca.dto.LeitorNewDTO;
import br.ufu.facom.bianca.repositories.LeitorRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;

@Service
public class LeitorService {
	
	@Autowired
	private LeitorRepository repo;
	
	public Leitor findById (Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Leitor> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Leitor.class.getName()));
	}
	
	public Leitor insert (Leitor obj) {
		// Metodo que insere uma nova Leitor 
		obj.setId(null);

		return repo.save(obj);
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
