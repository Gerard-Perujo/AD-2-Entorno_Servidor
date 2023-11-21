package unir.cajero.modelo.dao;

import java.util.Date;
import java.util.List;

import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;

public interface MovimientosDao {
	
	List<Movimientos> sacarTodo(int idCuenta);
	
	int movimientoIngreso(Cuentas cuenta, double cantidad, Date fecha, String operacion);
	
	int movimientoExtraer(Cuentas cuenta, double cantidad, Date fecha, String operacion);
	
	

}
