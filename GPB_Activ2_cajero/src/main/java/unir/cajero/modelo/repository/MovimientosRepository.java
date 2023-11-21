package unir.cajero.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import unir.cajero.modelo.entity.Movimientos;

public interface MovimientosRepository extends JpaRepository<Movimientos, Integer>{

	@Query("select c from Movimientos c JOIN FETCH c.cuenta where c.cuenta.idCuenta = :idCuenta")
	public List<Movimientos> buscarPorCuenta(@Param("idCuenta") int idCuenta);
}
