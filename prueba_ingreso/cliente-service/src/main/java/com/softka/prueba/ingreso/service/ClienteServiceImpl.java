package com.softka.prueba.ingreso.service;

import org.springframework.stereotype.Service;

import com.softka.common.service.CommonServiceImpl;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.repository.ClienteRepository;

@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{

}
