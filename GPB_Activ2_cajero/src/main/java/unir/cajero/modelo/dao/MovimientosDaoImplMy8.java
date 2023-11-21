package unir.cajero.modelo.dao;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;
import unir.cajero.modelo.repository.MovimientosRepository;

@Repository
public class MovimientosDaoImplMy8 implements MovimientosDao{
	
	private static int idAuto;

	
	static {
		idAuto = 0;
	}
	
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	@Autowired
	private CuentasDao cuedao;

	
	@Override
	public List<Movimientos> sacarTodo(int idCuenta) {
		try {
			if(cuedao.buscarUna(idCuenta) != null)
				return movimientosRepository.buscarPorCuenta(idCuenta);
			else
				return null;
			
		}
		catch (Exception e) {
	        e.printStackTrace();
	        return null;
		}
	}	
	
	@Override
	public int movimientoIngreso(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
			try {
				Movimientos movi = new Movimientos();
				movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
				movi.setCantidad(cantidad);
				movi.setFecha(fecha);
				movi.setOperacion("Ingreso");
				movi.setIdMovimiento(idAuto);
				movimientosRepository.save(movi);
		    } catch (Exception e) {
		        e.printStackTrace();
		       
		    }
			return 0;
		}

	@Override
	public int movimientoExtraer(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
	
		try {
			Movimientos movi = new Movimientos();
			movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
			movi.setCantidad(cantidad);
			movi.setFecha(fecha);
			movi.setOperacion("Retiro");
			movi.setIdMovimiento(idAuto);
			movimientosRepository.save(movi);
	    } catch (Exception e) {
	        e.printStackTrace();
	       
	    }
		return 0;
	}
}

	
	
	

