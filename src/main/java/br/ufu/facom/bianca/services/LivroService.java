package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Livro;
import br.ufu.facom.bianca.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repo;
	
	public Livro findById (Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Livro> obj = repo.findById(id);
		return obj.orElse(null); // Caso o obj nao tenha sido instanciado o retorno eh null
	}
}
