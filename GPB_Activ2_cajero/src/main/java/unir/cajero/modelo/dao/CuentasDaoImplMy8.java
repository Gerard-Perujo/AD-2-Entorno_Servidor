package unir.cajero.modelo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.repository.CuentasRepository;

/**
 * Clase que implementa los metodos de la interface Cuenta Dao
 * 
 * @author Gerard Perujo
 *
 */

@Repository
public class CuentasDaoImplMy8 implements CuentasDao{

	/**
	 * importamos la clase CuentasRepository para poder utilizar los metodos propios del JPA
	 */
	@Autowired
	private CuentasRepository cuentasRepository;
	
	
	/**
	 * metodo para buscar una cuenta pasandole su idCuenta, le pasamos la idCuenta que queremos
	 * encontrar y nos devuelve todo el objeto de la cuenta en cuestion con todos sus parametros
	 */
	@Override
	public Cuentas buscarUna(int idCuenta) {
		return cuentasRepository.findById(idCuenta).orElse(null);// utilizamos el metodo findbyId de JPA para buscar la cuenta en la BBDD
		}


	/**
	 * metodo para hacer un ingreso, le pasamos una cuenta y la cantidad del ingreso, en caso que se 
	 * haga el ingreso nos devolvera uno sino se ha realizado nos devolvera 0
	 * habra que usar try catch ya que va a levantar exepciones
	 */
	@Override
	public int ingreso(Cuentas cuenta, double saldo) {
		   try {
		        Cuentas comprovaCuenta = buscarUna(cuenta.getIdCuenta());// usamos el buscar una para buscar la cuenta y poder hacer una comprovacion mas adelante de si existe

		        if (comprovaCuenta != null) {// en caso de que la cuenta exista vamos a realizar el ingreso sino devolvera 0
		        		comprovaCuenta.setSaldo(comprovaCuenta.getSaldo() + saldo);// cogemos el saldo que tiene la cuenta y le sumamos la cantidad que nos passan
			            cuentasRepository.save(comprovaCuenta);// usamos el metodo .save de JPA para poder modificar el objeto con la cantidad que tiene despues de hacer la suma
			            return 1;
		        	
		        } else {
				     
		            return 0;
		        }
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        return -1; 
		    }

	}


	/**
	 * metodo para extraer dinero, le pasamos una cuenta y la cantidad que vamos a sacar, en caso que se 
	 * haga la retirada de dinero nos devolvera uno sino se ha realizado nos devolvera 0
	 * habra que usar try catch ya que va a levantar exepciones
	 */
	@Override
	public int extraer(Cuentas cuenta, double saldo) {
		 try {
		        Cuentas comprovaCuenta = buscarUna(cuenta.getIdCuenta());// usamos el buscar una para buscar la cuenta y poder hacer una comprovacion mas adelante de si existe

		        if (comprovaCuenta != null) {// en caso de que la cuenta exista vamos a realizar el ingreso sino devolvera 0
		        		comprovaCuenta.setSaldo(comprovaCuenta.getSaldo() - saldo);// cogemos el saldo que tiene la cuenta y le restamos la cantidad que nos passan
		        		if(comprovaCuenta.getSaldo() <= 0)// en caso de que la cuenta se quede a 0 al restar no va a realizar la operacion y nos devolvera 0
		        			return 0;
		        		else {
		        			cuentasRepository.save(comprovaCuenta);// usamos el metodo .save de JPA para poder modificar el objeto con la cantidad que tiene despues de hacer la suma
				            return 1;
		        		}
			            
		        	
		        } else {
				     
		            return 0;
		        }
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        return -1; 
		    }
	}
}

