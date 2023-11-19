package unir.cajero.modelo.dao;

import java.util.List;

import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;

public interface MovimientosDao {
	
	List<Movimientos> sacarTodo();
	

}
