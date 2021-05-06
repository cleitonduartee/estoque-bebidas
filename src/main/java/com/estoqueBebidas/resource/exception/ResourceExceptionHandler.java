package com.estoqueBebidas.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "ERRO DE VALIDAÇÂO";
		ValidationError err = new ValidationError(status.value(), error, e.getMessage(), request.getRequestURI());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.getErrors().add(new FieldMessage(x.getField(), x.getDefaultMessage()));
		}
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> erroEnun(HttpMessageNotReadableException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "ERRO NO RECURSO INFORMADO.";
		StandardError err = new StandardError(status.value(), error, "Categoria Informada não foi encontrada", request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> erroEnun(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "ERRO NO RECURSO INFORMADO.";
		StandardError err = new StandardError(status.value(), error, "Recurso informado não é compativel com tipo esperado.", request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
