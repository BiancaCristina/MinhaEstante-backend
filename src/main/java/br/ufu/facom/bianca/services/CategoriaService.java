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
		
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert (Categoria obj) {
		// Metodo que insere uma nova Categoria 
		obj.setId(null);

		return repo.save(obj);
	}	
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		// Metodo auxiliar que instancia um objeto do tipo Categoria a partir de um objeto do tipo CategoriaDTO
		return new Categoria(objDTO.getId(),objDTO.getNome());
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
}
