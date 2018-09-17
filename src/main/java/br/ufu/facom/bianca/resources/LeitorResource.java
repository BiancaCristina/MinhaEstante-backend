package br.ufu.facom.bianca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.dto.LeitorDTO;
import br.ufu.facom.bianca.dto.LeitorNewDTO;
import br.ufu.facom.bianca.resources.utils.URL;
import br.ufu.facom.bianca.services.LeitorService;

@RestController
@RequestMapping(value = "/leitores")
public class LeitorResource {

	@Autowired
	private LeitorService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// @PathVariable faz: Integer id == {id}
		// O "?" eh porque a resposta pode ser bem sucedida ou nao
		// ResponseEntity = armazena infos da resposta HTTP para o servico REST

		Leitor obj = service.find(id);
		return ResponseEntity.ok().body(obj); // Retorna uma resposta OK e o objeto como corpo
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody LeitorNewDTO objDTO) {
		// Metodo que insere uma nova Leitor
		Leitor obj = service.fromDTO(objDTO); 
		obj = service.insert(obj); 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid LeitorDTO objDTO, @PathVariable Integer id) {
		// Metodo que atualiza a Leitor
		
		Leitor obj = service.fromDTO(objDTO);
		
		obj.setId(id); // Comando feito pra garantir que o objeto que eu to passando tenha ID que eu quero atualizar
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		// Metodo que deleta uma Leitor
		service.delete(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public ResponseEntity<Page<LeitorDTO>> findPage(
			@RequestParam(name="page",defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(name="direction",defaultValue="ASC") String direction) 
	{	
		// Esse metodo faz a paginacao quando retorna a lista de todas as categorias 
		Page<Leitor> list = service.findPage(page,linesPerPage,orderBy,direction);
		
		// O codigo abaixo converte cada objeto da lista de Leitor em um objeto de LeitorDTO e forma uma lista de LeitorDTO
		// LeitorDTO mostrara apenas os dados que me interessam quando estou imprimindo a lista de Leitors(id,nome)
		Page<LeitorDTO> listDTO = list.map(obj -> new LeitorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/nomesearch", method=RequestMethod.GET)
	public ResponseEntity<List<LeitorDTO>> findByNome(@RequestParam(value="nome", defaultValue="") String nome) {
		// Esse metodo acha todas as editoras que contenham algo dessa string nome
		nome = URL.decodeParam(nome);
		
		List<Leitor> list = service.findByNome(nome);
		List<LeitorDTO> listDTO = list.stream().map(obj -> new LeitorDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
}
