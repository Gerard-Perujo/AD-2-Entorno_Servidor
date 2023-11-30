package unir.cajero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unir.cajero.modelo.entity.Cuentas;

/**
 * Creo la clase Cuentas repository que coge todos los metodos que tiene JPA
 * de esta manera esta clase se encargara de hacer todas las operaciones
 * necesarias con sus metodos propios
 * 
 * @author Geard Perujo
 *
 */
public interface CuentasRepository extends JpaRepository<Cuentas, Integer>{
	
}
