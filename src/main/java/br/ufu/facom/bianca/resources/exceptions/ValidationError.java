package br.ufu.facom.bianca.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
 	}
 	public List<FieldMessage> getErrors() {
		// Se eu deixasse "getList", no Json aparecia List
		// Colocando "getErrors", aparece "Errors" no Json
		return errors;
	}
 	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName,message));
	}
	
}