package com.softka.prueba.ingreso.cuenta.exception;

public class ClienteNotFoundException extends CustomRuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(String message) {
        super(message);
    }
}