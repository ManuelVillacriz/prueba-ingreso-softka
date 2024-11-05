package com.softka.prueba.ingreso.cuenta.exception;

public class CuentaNotFoundException extends CustomRuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaNotFoundException(String message) {
        super(message);
    }
}