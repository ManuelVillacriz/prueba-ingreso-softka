package com.softka.prueba.ingreso.cuenta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softka.prueba.ingreso.cuenta.dto.MovimientoDto;
import com.softka.prueba.ingreso.cuenta.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
	
	@Query(value = "SELECT m.saldo FROM Movimiento m WHERE m.cuenta.id = :cuentaId ORDER BY m.id DESC LIMIT 1")
    Double findLastSaldoByCuentaId(Long cuentaId);
	
	 @Query("SELECT new com.softka.prueba.ingreso.cuenta.dto.MovimientoDto(m.fecha, c.cliente.primerNombre||' '||c.cliente.primerApellido, c.numeroCuenta, c.tipoCuenta.descripcion, c.saldoInicial, c.estado, m.valor, m.saldo) " +
	           "FROM Movimiento m JOIN m.cuenta c " +
	           "WHERE c.cliente.id = :clienteId AND Date(m.fecha) BETWEEN :startDate AND :endDate")
	    List<MovimientoDto> findMovimientosByFechaBetween(@Param("clienteId") Long clienteId,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
