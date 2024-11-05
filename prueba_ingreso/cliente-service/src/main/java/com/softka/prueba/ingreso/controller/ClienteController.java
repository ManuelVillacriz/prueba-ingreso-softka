package com.softka.prueba.ingreso.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softka.common.controller.CommonController;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.service.ClienteService;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController extends CommonController<Cliente, ClienteService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Validated @RequestBody Cliente cliente, BindingResult result, @PathVariable(name = "id") Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Cliente> objeto = service.findById(id);
		
		if(objeto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente clienteDb = objeto.get();
		clienteDb.setPrimerNombre(cliente.getPrimerNombre());
		clienteDb.setSegundoNombre(cliente.getSegundoNombre());
		clienteDb.setPrimerApellido(cliente.getPrimerApellido());
		clienteDb.setSegundoApellido(cliente.getSegundoApellido());
		
		clienteDb.setEdadd(cliente.getEdadd());
		clienteDb.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
		clienteDb.setDireccion(cliente.getDireccion());
		clienteDb.setTelefono(cliente.getTelefono());
		
		clienteDb.setContraseña(cliente.getContraseña());
		clienteDb.setEstado(cliente.isEstado());
		
				
		return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(clienteDb));
	}
	

}
