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

import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.dto.EditoraDTO;
import br.ufu.facom.bianca.services.EditoraService;

@RestController
@RequestMapping(value="/editoras")
public class EditoraResource {
	
	@Autowired
	private EditoraService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// @PathVariable faz: Integer id == {id}
		// O "?" eh porque a resposta pode ser bem sucedida ou nao
		// ResponseEntity = armazena infos da resposta HTTP para o servico REST
		
		Editora obj = service.find(id);
		return ResponseEntity.ok().body(obj); // Retorna uma resposta OK e o objeto como corpo
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody EditoraDTO objDTO) {
		// Metodo que insere uma nova Editora
		Editora obj = service.fromDTO(objDTO); 
		obj = service.insert(obj); 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid EditoraDTO objDTO, @PathVariable Integer id) {
		// Metodo que atualiza a Editora
		
		Editora obj = service.fromDTO(objDTO);
		
		obj.setId(id); // Comando feito pra garantir que o objeto que eu to passando tenha ID que eu quero atualizar
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		// Metodo que deleta uma Editora
		service.delete(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public ResponseEntity<Page<EditoraDTO>> findPage(
			@RequestParam(name="page",defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(name="direction",defaultValue="ASC") String direction) 
	{	
		// Esse metodo faz a paginacao quando retorna a lista de todas as categorias 
		Page<Editora> list = service.findPage(page,linesPerPage,orderBy,direction);
		
		// O codigo abaixo converte cada objeto da lista de Editora em um objeto de EditoraDTO e forma uma lista de EditoraDTO
		// EditoraDTO mostrara apenas os dados que me interessam quando estou imprimindo a lista de Editoras(id,nome)
		Page<EditoraDTO> listDTO = list.map(obj -> new EditoraDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
