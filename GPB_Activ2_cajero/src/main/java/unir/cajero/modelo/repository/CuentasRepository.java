package unir.cajero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.cajero.modelo.entity.Cuentas;

public interface CuentasRepository extends JpaRepository<Cuentas, Integer>{
	
}
