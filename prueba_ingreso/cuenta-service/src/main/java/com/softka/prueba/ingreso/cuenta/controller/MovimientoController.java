package com.softka.prueba.ingreso.cuenta.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softka.common.controller.CommonController;
import com.softka.prueba.ingreso.cuenta.dto.MovimientoDto;
import com.softka.prueba.ingreso.cuenta.entity.Cuenta;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;
import com.softka.prueba.ingreso.cuenta.exception.CuentaNotFoundException;
import com.softka.prueba.ingreso.cuenta.exception.CustomRuntimeException;
import com.softka.prueba.ingreso.cuenta.service.CuentaService;
import com.softka.prueba.ingreso.cuenta.service.MovimientoService;

@RestController
@RequestMapping(value = "/api/movimientos")
public class MovimientoController extends CommonController<Movimiento, MovimientoService>{
	
	@Autowired
	private CuentaService cuentaService;
		

	@PostMapping("/{cuentaId}")
	public ResponseEntity<?> create(@Validated @RequestBody Movimiento movimiento, BindingResult result, @PathVariable Long cuentaId) {

		if (result.hasErrors()) {
	        return validar(result);
	    }
	    
	    Cuenta cuenta = cuentaService.findById(cuentaId)
	            .orElseThrow(() -> new CuentaNotFoundException("Cuenta no existe con ID: " + cuentaId));
	    
	    movimiento.setCuenta(cuenta);
	    
	    Movimiento savedMovimiento = service.save(movimiento, cuentaId);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedMovimiento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Validated @RequestBody Movimiento movimiento, BindingResult result, @PathVariable(name = "id") Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Movimiento> objeto = service.findById(id);
		
		if(objeto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Movimiento movimientoDb = objeto.get();
		
		movimientoDb.setTipoMovimiento(movimiento.getTipoMovimiento());
		movimientoDb.setValor(movimiento.getValor());
						
		return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(movimientoDb,movimientoDb.getCuenta().getId()));
	}
	
	 @GetMapping("/reportes/fechas")
	    public ResponseEntity<List<MovimientoDto>> obtenerMovimientosPorRangoFechas(
	    		@RequestParam Long clienteId, @RequestParam String startDate, @RequestParam String endDate) {
	        
	        LocalDate start = LocalDate.parse(startDate); 
	        LocalDate end = LocalDate.parse(endDate); 

	        List<MovimientoDto> movimientos = service.obtenerMovimientosPorRangoFechas(clienteId,start, end);
	        return ResponseEntity.ok(movimientos);
	    }
}
