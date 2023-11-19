package unir.cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;
import unir.cajero.modelo.repository.MovimientosRepository;

@Repository
public class MovimientosDaoImplMy8 implements MovimientosDao{
	
	@Autowired
	private MovimientosRepository movimientosRepository;

	@Override
	public List<Movimientos> sacarTodo() {
		return movimientosRepository.findAll();
	}


}
