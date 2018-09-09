package br.ufu.facom.bianca.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		// Metodo que deleta uma Autor
		service.delete(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public ResponseEntity<Page<AutorDTO>> findPage(
			@RequestParam(name="page",defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(name="direction",defaultValue="ASC") String direction) 
	{	
		// Esse metodo faz a paginacao quando retorna a lista de todas as categorias 
		Page<Autor> list = service.findPage(page,linesPerPage,orderBy,direction);
		
		// O codigo abaixo converte cada objeto da lista de Autor em um objeto de AutorDTO e forma uma lista de AutorDTO
		// AutorDTO mostrara apenas os dados que me interessam quando estou imprimindo a lista de Autors(id,nome)
		Page<AutorDTO> listDTO = list.map(obj -> new AutorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
