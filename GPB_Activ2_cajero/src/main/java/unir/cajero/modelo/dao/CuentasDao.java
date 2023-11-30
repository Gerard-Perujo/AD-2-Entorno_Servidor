package unir.cajero.modelo.dao;

import unir.cajero.modelo.entity.Cuentas;

/**
 * Interface de Cuentas donde tendremos nuestros metodos que utilizaremos para
 * sacar la informacion
 * 
 * @author Gerard Perujo
 *
 */
public interface CuentasDao {
	
	Cuentas buscarUna(int idCuenta);
	int ingreso(Cuentas cuenta, double saldo);
	int extraer(Cuentas cuenta, double saldo);

}
