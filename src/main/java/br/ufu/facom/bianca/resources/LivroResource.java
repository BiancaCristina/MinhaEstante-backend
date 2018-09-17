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

import br.ufu.facom.bianca.domain.Livro;
import br.ufu.facom.bianca.dto.LivroDTO;
import br.ufu.facom.bianca.resources.utils.URL;
import br.ufu.facom.bianca.services.LivroService;

@RestController
@RequestMapping(value="/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// @PathVariable faz: Integer id == {id}
		// O "?" eh porque a resposta pode ser bem sucedida ou nao
		// ResponseEntity = armazena infos da resposta HTTP para o servico REST
		
		Livro obj = service.find(id); // Acha um obj do tipo Livro
		
		LivroDTO newObj = new LivroDTO(obj); // Instancia um LivroDTO a partir de Livro
		
		return ResponseEntity.ok().body(newObj); 
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody LivroDTO objDTO) {
		// Metodo que insere uma nova Livro
		Livro obj = service.fromDTO(objDTO); 
		obj = service.insert(obj); 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid LivroDTO objDTO, @PathVariable Integer id) {
		// Metodo que atualiza a Livro
		
		Livro obj = service.fromDTO(objDTO);
		
		obj.setId(id); // Comando feito pra garantir que o objeto que eu to passando tenha ID que eu quero atualizar
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		// Metodo que deleta uma Livro
		service.delete(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page/nomesearch", method=RequestMethod.GET)
	public ResponseEntity<Page<LivroDTO>> findByNome(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(name="page",defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage",defaultValue="15") Integer linesPerPage, 
			@RequestParam(name="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(name="direction",defaultValue="ASC") String direction) {
		// Esse metodo acha todos os objetos do tipo Livro que contenham algo da string nome
		nome = URL.decodeParam(nome);
		
		Page<Livro> list = service.findPageByNome(nome, page, linesPerPage, orderBy,direction);
		Page<LivroDTO> listDTO = list.map(obj -> new LivroDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
}
