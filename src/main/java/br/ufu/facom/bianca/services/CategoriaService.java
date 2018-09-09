package br.ufu.facom.bianca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.dto.CategoriaDTO;
import br.ufu.facom.bianca.repositories.CategoriaRepository;
import br.ufu.facom.bianca.resources.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		// Esse metodo busca uma categoria a partir de seu ID
		
		Optional<Categoria> obj = repo.findById(id);
		
		// O retorno abaixo trata o erro de caso o obj nao tenha sido encontrado usando o tratamento de erro criado
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert (Categoria obj) {
		obj.setId(null);
		// Se esse obj tiver algum ID, o metodo save vai considerar que eh uma atualizacao e nao uma insercao
		// Logo, o comando acima garante que estou inserindo um objeto do tipo Categoria com ID nulo
		return repo.save(obj);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Categoria a partir de um objeto do tipo CategoriaDTO
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
}
