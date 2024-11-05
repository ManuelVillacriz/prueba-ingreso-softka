package com.softka.prueba.ingreso.cuenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softka.common.service.entity.Cliente;
import com.softka.prueba.ingreso.cuenta.entity.Cuenta;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;
import com.softka.prueba.ingreso.cuenta.entity.TipoCuenta;
import com.softka.prueba.ingreso.cuenta.entity.TipoMovimiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteCuentaMovimientoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    private Cliente cliente;
    private Cuenta cuenta;
    private Movimiento movimiento;
    private TipoCuenta tipoCuenta;
    private TipoMovimiento tipoMovimiento;

    private static final String CLIENTE_URL = "http://localhost:9090/api/clientes";
    private static final String CUENTA_URL = "http://localhost:9091/api/cuentas";
    private static final String MOVIMIENTO_URL = "http://localhost:9091/api/movimientos";

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setPrimerNombre("Marianela");
        cliente.setSegundoNombre("Esther");
        cliente.setPrimerApellido("Montalvo");
        cliente.setSegundoApellido("Rojas");
        cliente.setEdadd(45);
        cliente.setContraseña("securePassword123");
        cliente.setEstado(true);
        cliente.setNumeroIdentificacion("12345678");
        cliente.setDireccion("Avenida las palmas");
        cliente.setTelefono("987654321");

        tipoCuenta = new TipoCuenta();
        tipoCuenta.setId(1L); 
        tipoCuenta.setCodigo("A");
        tipoCuenta.setDescripcion("Ahorros");

        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("225487");
        cuenta.setSaldoInicial(1000.00);
        //cuenta.setTipoCuenta(tipoCuenta); 
        cuenta.setCliente(cliente);

        tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setId(2L); 
        tipoMovimiento.setCodigo("C");
        tipoMovimiento.setDescripcion("Consignacion");

        movimiento = new Movimiento();
        movimiento.setValor(200.00);
        movimiento.setTipoMovimiento(tipoMovimiento); 
        movimiento.setCuenta(cuenta);
    }

    @Test
    public void testCrearClienteCuentaYMovimiento() throws Exception {        
        ResponseEntity<Cliente> clienteResponse = restTemplate.postForEntity(CLIENTE_URL, cliente, Cliente.class);
        assertEquals(HttpStatus.CREATED, clienteResponse.getStatusCode());

        cuenta.setCliente(clienteResponse.getBody());
        ResponseEntity<Cuenta> cuentaResponse = restTemplate.postForEntity(CUENTA_URL, cuenta, Cuenta.class);
        assertEquals(HttpStatus.CREATED, cuentaResponse.getStatusCode());
        Long cuentaId = cuentaResponse.getBody().getId();

        Movimiento movimientoRequest = new Movimiento();
        movimientoRequest.setValor(200.00);
        movimientoRequest.setTipoMovimiento(tipoMovimiento); 

        mockMvc.perform(post(MOVIMIENTO_URL + "/" + cuentaId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movimientoRequest)))
                .andExpect(status().isCreated());
    }
}
