package br.com.victorreis.gerenciatasks.infraestrutura.web;

import org.springframework.validation.Errors;

public class RestResponseError {
	private String error;
	private RestResponseError() {
		
	}
	
	public String getError() {
		return error;
	}
	
	public static RestResponseError fromValidationError(Errors errors) {
		RestResponseError resp = new RestResponseError();
		StringBuilder sb = new StringBuilder();
		errors.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append(". "));
		resp.error = sb.toString();
		return resp;
	}
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError resp = new RestResponseError();
		resp.error = message;
		return resp;
	}
}
