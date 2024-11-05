package com.softka.common.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes", schema = "clientes")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Cliente extends Persona {

	@NotNull
	private String contraseña;
	private boolean estado;
	

	@PrePersist
    protected void onCreate() {
        this.estado = true;
    }
}
