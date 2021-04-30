package com.estoqueBebidas.service.exception;

public class ProductAlreadyRegisteredtException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProductAlreadyRegisteredtException(String msg) {
		super(msg);
	}

}
