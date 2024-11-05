package com.softka.prueba.ingreso.cuenta.exception;

public class ClienteServiceUnavailableException extends CustomRuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteServiceUnavailableException(String message) {
        super(message);
    }
}