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

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.dto.CategoriaDTO;
import br.ufu.facom.bianca.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// Metodo que procura uma Categoria por seu ID
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		// Metodo que insere uma nova Categoria
		Categoria obj = service.fromDTO(objDTO); 
		obj = service.insert(obj); 	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid CategoriaDTO objDTO, @PathVariable Integer id) {
		// Metodo que atualiza a Categoria
		
		Categoria obj = service.fromDTO(objDTO);
		
		obj.setId(id); // Comando feito pra garantir que o objeto que eu to passando tenha ID que eu quero atualizar
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		// Metodo que deleta uma Categoria
		service.delete(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		// Metodo que busca todas as categoriasDTO (sem paginacao)
		List <Categoria> list = service.findAll();
		
		// O codigo abaixo converte cada objeto da lista de Categoria em um objeto de CategoriaDTO e forma uma lista de CategoriaDTO
		// CategoriaDTO mostrara apenas os dados que me interessam quando estou imprimindo a lista de Categorias(id,nome)
		List <CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name="page",defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(name="direction",defaultValue="ASC") String direction) 
	{	
		// Esse metodo faz a paginacao quando retorna a lista de todas as categorias 
		Page<Categoria> list = service.findPage(page,linesPerPage,orderBy,direction);
		
		// O codigo abaixo converte cada objeto da lista de Categoria em um objeto de CategoriaDTO e forma uma lista de CategoriaDTO
		// CategoriaDTO mostrara apenas os dados que me interessam quando estou imprimindo a lista de Categorias(id,nome)
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
