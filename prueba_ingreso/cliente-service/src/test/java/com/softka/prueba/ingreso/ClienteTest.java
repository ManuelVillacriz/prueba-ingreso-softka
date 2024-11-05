package com.softka.prueba.ingreso;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.softka.common.service.entity.Cliente;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setPrimerNombre("Marianela");
        cliente.setPrimerApellido("Montalvo");
        cliente.setContraseña("securePassword123");
        cliente.setEstado(true);
        cliente.setNumeroIdentificacion("12345678");
        cliente.setTelefono("987654321");
    }

    @Test
    public void testClienteAttributes() {
        assertEquals("Marianela", cliente.getPrimerNombre());
        assertEquals("Montalvo", cliente.getPrimerApellido());
        assertEquals("securePassword123", cliente.getContraseña());
        assertTrue(cliente.isEstado());
        assertEquals("12345678", cliente.getNumeroIdentificacion());
        assertEquals("987654321", cliente.getTelefono());
    }

    @Test
    public void testClienteSettersAndGetters() {
        cliente.setSegundoNombre("Ana");
        assertEquals("Ana", cliente.getSegundoNombre());
        
        cliente.setDireccion("Calle Falsa 123");
        assertEquals("Calle Falsa 123", cliente.getDireccion());
        
        cliente.setEdadd(30);
        assertEquals(30, cliente.getEdadd());
    }
}