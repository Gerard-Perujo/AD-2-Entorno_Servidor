package unir.cajero.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unir.cajero.modelo.entity.Movimientos;

public interface MovimientosRepository extends JpaRepository<Movimientos, Integer>{

}
