package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria findById (Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null); // Caso o obj nao tenha sido instanciado o retorno eh null
	}
}
