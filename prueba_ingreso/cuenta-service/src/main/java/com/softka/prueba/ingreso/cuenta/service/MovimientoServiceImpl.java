package com.softka.prueba.ingreso.cuenta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softka.common.service.CommonServiceImpl;
import com.softka.prueba.ingreso.cuenta.dto.MovimientoDto;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;
import com.softka.prueba.ingreso.cuenta.exception.CuentaNotFoundException;
import com.softka.prueba.ingreso.cuenta.exception.SaldoInsuficienteException;
import com.softka.prueba.ingreso.cuenta.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl extends CommonServiceImpl<Movimiento, MovimientoRepository> implements MovimientoService{

	@Value("${codigo.tipo.movimiento.retiro}")
    private String variableRetiro;
	
	@Autowired
	private CuentaService cuentaService;
			
	@Override
	@Transactional
	public Movimiento save(Movimiento movimiento, Long cuentaId) {
		
		Double ultimoSaldo = repository.findLastSaldoByCuentaId(cuentaId);
	    boolean esRetiro = movimiento.getTipoMovimiento().getCodigo().equals(variableRetiro);
	    
	    verificarSaldo(ultimoSaldo, movimiento, esRetiro);

	    Double nuevoSaldo = calcularNuevoSaldo(ultimoSaldo, movimiento, cuentaId);
	    movimiento.setSaldo(nuevoSaldo);
	    
	    movimiento.setCuenta(cuentaService.findById(cuentaId).orElseThrow(() -> 
	        new CuentaNotFoundException("Cuenta no encontrada con ID: " + cuentaId)));

	    return repository.save(movimiento);
	}
	
		
	@Transactional(readOnly = true)
	private void verificarSaldo(Double ultimoSaldo, Movimiento movimiento, boolean esRetiro) {
	    if (esRetiro && (ultimoSaldo == null ||  Math.abs(movimiento.getValor()) > ultimoSaldo)) {
	        throw new SaldoInsuficienteException("El retiro de " + Math.abs(movimiento.getValor()) + 
	            " excede el saldo disponible de " + ultimoSaldo);
	    }
	}

	@Transactional(readOnly = true)
	private Double calcularNuevoSaldo(Double ultimoSaldo, Movimiento movimiento, Long cuentaId) {
	    if (ultimoSaldo != null) {
	        return ultimoSaldo + movimiento.getValor();
	    } else {
	        Double saldoInicial = cuentaService.findById(cuentaId)
	            .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con ID: " + cuentaId))
	            .getSaldoInicial();
	        return saldoInicial + movimiento.getValor();
	    }
	}


	@Transactional(readOnly = true)
	 public List<MovimientoDto> obtenerMovimientosPorRangoFechas(Long clienteId,LocalDate startDate, LocalDate endDate) {
        return repository.findMovimientosByFechaBetween(clienteId, startDate, endDate);
    }
}
