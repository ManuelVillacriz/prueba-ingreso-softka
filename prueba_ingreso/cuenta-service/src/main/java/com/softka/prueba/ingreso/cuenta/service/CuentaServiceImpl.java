package com.softka.prueba.ingreso.cuenta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softka.common.service.CommonServiceImpl;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.cuenta.client.ClienteClient;
import com.softka.prueba.ingreso.cuenta.entity.Cuenta;
import com.softka.prueba.ingreso.cuenta.exception.ClienteNotFoundException;
import com.softka.prueba.ingreso.cuenta.exception.ClienteServiceUnavailableException;
import com.softka.prueba.ingreso.cuenta.repository.CuentaRepository;

import feign.FeignException;

@Service
public class CuentaServiceImpl extends CommonServiceImpl<Cuenta, CuentaRepository> implements CuentaService {

	@Autowired
	private CuentaRepository repository;

	@Autowired
	private ClienteClient clienteClient;

	@Override
	@Transactional
	public Cuenta save(Cuenta cuenta, Long clienteId) {
		
		Cliente cliente = getClienteById(clienteId)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado."));

		cuenta.setCliente(cliente);

		return repository.save(cuenta);
	}

	@Transactional(readOnly = true)
	private Optional<Cliente> getClienteById(Long clienteId) {
		try {
			ResponseEntity<Cliente> response = clienteClient.getClienteById(clienteId);
			if (response.getStatusCode().is2xxSuccessful()) {
				return Optional.ofNullable(response.getBody());
			}else {
	            throw new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado.");
	        }
		} catch (FeignException.NotFound ex) {
	        throw new ClienteNotFoundException("Cliente con ID " + clienteId + " no encontrado.");
	    } 
	}
}
