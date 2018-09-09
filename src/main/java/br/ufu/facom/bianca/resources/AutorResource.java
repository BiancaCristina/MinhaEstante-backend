package br.ufu.facom.bianca.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufu.facom.bianca.domain.Autor;
import br.ufu.facom.bianca.dto.AutorDTO;
import br.ufu.facom.bianca.services.AutorService;

@RestController
@RequestMapping(value="/autores")
public class AutorResource {
	
	@Autowired
	private AutorService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// @PathVariable faz: Integer id == {id}
		// O "?" eh porque a resposta pode ser bem sucedida ou nao
		// ResponseEntity = armazena infos da resposta HTTP para o servico REST
		
		Autor obj = service.find(id);
		return ResponseEntity.ok().body(obj); // Retorna uma resposta OK e o objeto como corpo
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody AutorDTO objDTO) {
		// Metodo que insere uma nova Autor
		Autor obj = service.fromDTO(objDTO); 
		obj = service.insert(obj); 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid AutorDTO objDTO, @PathVariable Integer id) {
		// Metodo que atualiza a Autor
		
		Autor obj = service.fromDTO(objDTO);
		
		obj.setId(id); // Comando feito pra garantir que o objeto que eu to passando tenha ID que eu quero atualizar
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
}
