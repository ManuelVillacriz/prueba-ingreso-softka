package com.softka.prueba.ingreso.cuenta.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDto {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecha;
	private String cliente;
	private String numeroCuenta;
	private String tipo;
	private double saldoInicial;
	private boolean estado;
	private double movimiento;
	private double saldoDisponible;

	public MovimientoDto(LocalDateTime fecha, String cliente, String numeroCuenta, String tipo, double saldoInicial,
			boolean estado, double movimiento, double saldoDisponible) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.movimiento = movimiento;
		this.saldoDisponible = saldoDisponible;
	}
	
	public MovimientoDto() {
		
	}
}
