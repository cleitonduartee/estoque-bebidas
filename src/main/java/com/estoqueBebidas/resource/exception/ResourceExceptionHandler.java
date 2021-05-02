package com.estoqueBebidas.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estoqueBebidas.service.exception.LimitSecaoException;
import com.estoqueBebidas.service.exception.ProductAlreadyRegisteredtException;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "ERRO NO RECURSO INFORMADO.";
		StandardError err = new StandardError(status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(LimitSecaoException.class)
	public ResponseEntity<StandardError> limitSecaoException(LimitSecaoException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "ERRO NO ESTOQUE";
		StandardError err = new StandardError(status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(ProductAlreadyRegisteredtException.class)
	public ResponseEntity<StandardError> productAlreadyRegisteredtException(ProductAlreadyRegisteredtException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "ERRO DE DUPLICIDADE";
		StandardError err = new StandardError(status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
