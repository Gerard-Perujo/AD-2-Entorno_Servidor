package unir.cajero.modelo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;
import unir.cajero.modelo.repository.MovimientosRepository;

/**
 * Clase que implementa los metodos de la interface movimientos Dao
 * 
 * @author Gerard Perujo
 *
 */

@Repository 
public class MovimientosDaoImplMy8 implements MovimientosDao{
	
	private static int idAuto;// creo una variable idAuto para que ponga automaticamente una id

	
	static {
		idAuto = 0;
	}
	
	
	/**
	 * importamos la clase MovimientosRepository para poder utilizar los metodos propios del JPA
	 */
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	/**
	 * importamos la clase CuentasDao para poder utilizar los metodos propios del JPA
	 */
	@Autowired
	private CuentasDao cuedao;

	
	/**
	 * metodo que saca una lista de todos los movimientos pasandole el idCuenta
	 * ete metodo levanta exepciones por lo tanto haremos un try catch para cogerlas.
	 */
	@Override
	public List<Movimientos> sacarTodo(int idCuenta) {
		try {
			if(cuedao.buscarUna(idCuenta) != null)//utilizamos el metodo buscar una en caso de de exista sacaremos todos los movimientos sino existe sale null
				return movimientosRepository.buscarPorCuenta(idCuenta);
			else
				return null;
			
		}
		catch (Exception e) {
	        e.printStackTrace();
	        return null;
		}
	}	
	
	/**
	 * metodo que usaremos para sacar todos los movimientos de Ingreso que se han realizado, sacando el 
	 * id de la cuenta, la cantidad que se ha ingresado la fecha cuando se ha realizado el ingreso
	 * y que tipo de operacion que se ha realizado.
	 * vamos a usar el try catch ya que levanta exepciones
	 */
	@Override
	public int movimientoIngreso(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
			try {
				
				Movimientos movi = new Movimientos();
				movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
				movi.setCantidad(cantidad);
				movi.setFecha(fecha);
				movi.setOperacion("Ingreso");
				movi.setIdMovimiento(idAuto);
				movimientosRepository.save(movi);// utilizaremos el .save para modificar el objeto movimiento y que se le añaden todos los valores que le hemos puesto
		    } catch (Exception e) {
		        e.printStackTrace();
		       
		    }
			return 0;
		}

	
	/**
	 * metodo que usaremos para sacar todos los movimientos de Extraer dinero que se han realizado, sacando el 
	 * id de la cuenta, la cantidad que se ha ingresado la fecha cuando se ha realizado el ingreso
	 * y que tipo de operacion que se ha realizado.
	 * vamos a usar el try catch ya que levanta exepciones
	 */
	@Override
	public int movimientoExtraer(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
	
		try {
			Movimientos movi = new Movimientos();
			movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
			movi.setCantidad(cantidad);
			movi.setFecha(fecha);
			movi.setOperacion("Retiro");
			movi.setIdMovimiento(idAuto);
			movimientosRepository.save(movi);// utilizaremos el .save para modificar el objeto movimiento y que se le añaden todos los valores que le hemos puesto
	
	    } catch (Exception e) {
	        e.printStackTrace();
	       
	    }
		return 0;
	}

	/**
	 * metodo que usaremos para sacar todos los movimientos de Ingreso de una transferencia que se han realizado, sacando el 
	 * id de la cuenta, la cantidad que se ha ingresado la fecha cuando se ha realizado el ingreso
	 * y que tipo de operacion que se ha realizado.
	 * vamos a usar el try catch ya que levanta exepciones
	 */
	@Override
	public int movimientoIngresoTransferencia(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
		try {
			Movimientos movi = new Movimientos();
			movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
			movi.setCantidad(cantidad);
			movi.setFecha(fecha);
			movi.setOperacion("Ingreso Transferencia");
			movi.setIdMovimiento(idAuto);
			movimientosRepository.save(movi);// utilizaremos el .save para modificar el objeto movimiento y que se le añaden todos los valores que le hemos puesto
	  
	    } catch (Exception e) {
	        e.printStackTrace();
	       
	    }
		return 0;
	}

	/**
	 * metodo que usaremos para sacar todos los movimientos de Extraer de una transferencia que se han realizado, sacando el 
	 * id de la cuenta, la cantidad que se ha ingresado la fecha cuando se ha realizado el ingreso
	 * y que tipo de operacion que se ha realizado.
	 * vamos a usar el try catch ya que levanta exepciones
	 */
	@Override
	public int movimientoExtraerTransferencia(Cuentas cuenta, double cantidad, Date fecha, String operacion) {
		try {
			Movimientos movi = new Movimientos();
			movi.setCuenta(cuedao.buscarUna(cuenta.getIdCuenta()));
			movi.setCantidad(cantidad);
			movi.setFecha(fecha);
			movi.setOperacion("Retiro Transferencia");
			movi.setIdMovimiento(idAuto);
			movimientosRepository.save(movi);// utilizaremos el .save para modificar el objeto movimiento y que se le añaden todos los valores que le hemos puesto
	  
	    } catch (Exception e) {
	        e.printStackTrace();
	       
	    }
		return 0;
	}
}

	
	
	

