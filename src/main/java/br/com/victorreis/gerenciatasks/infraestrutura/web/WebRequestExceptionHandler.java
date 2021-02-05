package br.com.victorreis.gerenciatasks.infraestrutura.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.victorreis.gerenciatasks.domain.task.TaksDuplicadaException;

@RestControllerAdvice
public class WebRequestExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handleException(RepositoryConstraintViolationException e) {
		return RestResponseError.fromValidationError(e.getErrors());
	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handleException(TaksDuplicadaException e) {
		return RestResponseError.fromMessage(e.getMessage());
	}

}
