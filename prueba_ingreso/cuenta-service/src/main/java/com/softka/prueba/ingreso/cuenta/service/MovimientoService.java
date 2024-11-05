package com.softka.prueba.ingreso.cuenta.service;

import java.time.LocalDate;
import java.util.List;

import com.softka.common.service.CommonService;
import com.softka.prueba.ingreso.cuenta.dto.MovimientoDto;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;

public interface MovimientoService extends CommonService<Movimiento>{
	
	 public Movimiento save(Movimiento movimiento, Long cuentaId);
	 
	 public List<MovimientoDto> obtenerMovimientosPorRangoFechas(Long clienteId, LocalDate startDate, LocalDate endDate);
}
