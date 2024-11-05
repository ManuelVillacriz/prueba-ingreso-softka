package com.softka.prueba.ingreso.cuenta.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softka.common.controller.CommonController;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.cuenta.entity.Cuenta;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;
import com.softka.prueba.ingreso.cuenta.exception.CuentaNotFoundException;
import com.softka.prueba.ingreso.cuenta.service.CuentaService;


@RestController
@RequestMapping(value = "/api/cuentas")
public class CuentaController extends CommonController<Cuenta, CuentaService>{
	
	
	@PostMapping("/{clienteId}")
	public ResponseEntity<?> create(@Validated @RequestBody Cuenta cuenta, BindingResult result, @PathVariable Long clienteId) {

		if (result.hasErrors()) {
	        return validar(result);
	    }
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cuenta, clienteId));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Validated @RequestBody Cuenta cuenta, BindingResult result, @PathVariable(name = "id") Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Cuenta> objeto = service.findById(id);
		
		if(objeto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cuenta cuentaDb = objeto.get();
		
		cuentaDb.setTipoCuenta(cuenta.getTipoCuenta());
		cuentaDb.setNumeroCuenta(cuenta.getNumeroCuenta());
		//cuentaDb.setSaldoInicial(cuenta.getSaldoInicial());
						
		return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(cuentaDb));
	}
	
	

}
