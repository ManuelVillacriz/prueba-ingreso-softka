package com.softka.prueba.ingreso.cuenta.entity;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "movimientos", schema = "cuentas",
uniqueConstraints = @UniqueConstraint(columnNames = {"fecha", "tipo_movimiento_id"}))
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta; 
	
	private LocalDateTime fecha;
	
	@NotNull
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_movimiento_id")	
	private TipoMovimiento tipoMovimiento;
	
	private double valor;
	private double saldo;
	 
	
	@PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }	
	
}
