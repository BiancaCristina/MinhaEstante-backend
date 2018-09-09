package br.ufu.facom.bianca.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.services.LeitorService;

@RestController
@RequestMapping(value = "/leitores")
public class LeitorResource {

	@Autowired
	private LeitorService leitorService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// @PathVariable faz: Integer id == {id}
		// O "?" eh porque a resposta pode ser bem sucedida ou nao
		// ResponseEntity = armazena infos da resposta HTTP para o servico REST

		Leitor obj = leitorService.findById(id);
		return ResponseEntity.ok().body(obj); // Retorna uma resposta OK e o objeto como corpo
	}
}
