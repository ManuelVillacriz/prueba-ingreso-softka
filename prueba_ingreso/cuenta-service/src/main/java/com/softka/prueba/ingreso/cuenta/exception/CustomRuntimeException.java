package com.softka.prueba.ingreso.cuenta.exception;

public class CustomRuntimeException  extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomRuntimeException(String message) {
        super(message);
    }
}