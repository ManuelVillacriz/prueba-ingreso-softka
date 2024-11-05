package com.softka.prueba.ingreso.cuenta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softka.common.service.entity.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas", schema = "cuentas",
uniqueConstraints = @UniqueConstraint(columnNames = {"numero_cuenta", "tipo_cuenta_id"}))
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@NotNull
	@NotEmpty
	private String numeroCuenta;
	
	//@NotNull
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_cuenta_id")	
	private TipoCuenta tipoCuenta;
	
	private Double saldoInicial;
	private boolean estado;
	
	@PrePersist
    protected void onCreate() {
        this.estado = true;
    }


}
