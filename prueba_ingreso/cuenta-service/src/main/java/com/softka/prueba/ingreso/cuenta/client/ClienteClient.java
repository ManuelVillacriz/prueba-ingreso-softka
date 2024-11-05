package com.softka.prueba.ingreso.cuenta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softka.common.service.entity.Cliente;

@FeignClient(name = "cliente-service", url = "http://localhost:9090/api/clientes")
public interface ClienteClient {

    @GetMapping("/{id}")
    ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id);

}
