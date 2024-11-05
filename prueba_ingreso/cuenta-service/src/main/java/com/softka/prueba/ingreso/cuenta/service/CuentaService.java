package com.softka.prueba.ingreso.cuenta.service;

import com.softka.common.service.CommonService;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.cuenta.entity.Cuenta;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;

public interface CuentaService extends CommonService<Cuenta>{
	
	
	 public Cuenta save(Cuenta cuenta, Long clienteId); 
	

}
