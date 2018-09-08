package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.repositories.EditoraRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repo;
	
	public Editora findById (Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Editora> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Editora.class.getName()));
	}
}
