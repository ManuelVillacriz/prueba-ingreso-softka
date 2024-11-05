package com.softka.prueba.ingreso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softka.common.service.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
