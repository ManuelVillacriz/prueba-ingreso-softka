package com.softka.prueba.ingreso.cuenta.exception;

public class SaldoInsuficienteException extends CustomRuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String message) {
        super(message);
    }
}