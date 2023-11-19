package unir.cajero.modelo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.repository.CuentasRepository;

@Repository
public class CuentasDaoImplMy8 implements CuentasDao{

	@Autowired
	private CuentasRepository cuentasRepository;
	
	
	@Override
	public Cuentas buscarUna(int idCuenta) {
		return cuentasRepository.findById(idCuenta).orElse(null);
		}


	@Override
	public int ingreso(Cuentas cuenta, double saldo) {
		   try {
		        Cuentas comprovaCuenta = buscarUna(cuenta.getIdCuenta());

		        if (comprovaCuenta != null) {
		        		comprovaCuenta.setSaldo(comprovaCuenta.getSaldo() + saldo);
			            cuentasRepository.save(comprovaCuenta);
			            return 1;
		        	
		        } else {
				     
		            return 0;
		        }
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        return -1; 
		    }

	}


	@Override
	public int extraer(Cuentas cuenta, double saldo) {
		 try {
		        Cuentas comprovaCuenta = buscarUna(cuenta.getIdCuenta());

		        if (comprovaCuenta != null) {
		        		comprovaCuenta.setSaldo(comprovaCuenta.getSaldo() - saldo);
		        		if(comprovaCuenta.getSaldo() <= 0)
		        			return 0;
		        		else {
		        			cuentasRepository.save(comprovaCuenta);
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

