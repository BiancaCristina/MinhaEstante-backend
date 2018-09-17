package br.ufu.facom.bianca.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.dto.LeitorNewDTO;
import br.ufu.facom.bianca.repositories.LeitorRepository;
import br.ufu.facom.bianca.resources.exceptions.FieldMessage;

public class LeitorInsertValidator implements ConstraintValidator<LeitorInsert, LeitorNewDTO> {
	
	@Autowired
	private LeitorRepository repo;
	
	@Override
	public void initialize(LeitorInsert ann) {}

	@Override
	public boolean isValid(LeitorNewDTO objDTO, ConstraintValidatorContext context) {
		// Metodo que verifica se esta valido para insercao
		
		List<FieldMessage> list = new ArrayList<>();

		Leitor aux = repo.findByEmail(objDTO.getEmail());
		
		if (aux != null)
		{
			// Se aux != null, entao alguem ja possui esse email
			list.add(new FieldMessage("email","Email ja existente"));
		}
		
		for (FieldMessage e : list) {
			// Isso aqui serve para converter meus FieldMessage(meus erros customizados) em erros do framework e adicionar na lista do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		
		return list.isEmpty(); // Se a lista for vazia, significa que nao houve erros, logo eh valido.
	}
}