package unir.cajero.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import unir.cajero.modelo.entity.Movimientos;


/**
 * Creo la clase Movimientos repository que coge todos los metodos que tiene JPA
 * de esta manera esta clase se encargara de hacer todas las operaciones
 * necesarias con sus metodos propios
 * 
 * @author Geard Perujo
 *
 */
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer>{

	/**
	 * En este caso me creo un metodo que no tiene JPA para poder sacar los movimientos
	 * de una cuenta en concreto pasandole el idCuenta 
	 * 
	 * @param idCuenta : id de la cuenta que queremos sacar los movimientos
	 * 
	 * @return devuelve todos los movimientos que se han realizado en la cuenta
	 */
	@Query("select c from Movimientos c JOIN FETCH c.cuenta where c.cuenta.idCuenta = :idCuenta")
	public List<Movimientos> buscarPorCuenta(@Param("idCuenta") int idCuenta);
}
