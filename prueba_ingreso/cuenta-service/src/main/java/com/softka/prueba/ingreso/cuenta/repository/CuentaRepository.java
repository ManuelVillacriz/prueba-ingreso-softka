package com.softka.prueba.ingreso.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softka.prueba.ingreso.cuenta.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
