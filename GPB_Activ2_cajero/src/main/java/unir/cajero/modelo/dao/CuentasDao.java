package unir.cajero.modelo.dao;

import unir.cajero.modelo.entity.Cuentas;

public interface CuentasDao {
	
	Cuentas buscarUna(int idCuenta);
	int ingreso(Cuentas cuenta, double saldo);
	int extraer(Cuentas cuenta, double saldo);

}
