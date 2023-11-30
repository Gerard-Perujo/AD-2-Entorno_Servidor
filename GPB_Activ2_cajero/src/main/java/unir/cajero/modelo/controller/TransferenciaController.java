package unir.cajero.modelo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import unir.cajero.modelo.dao.CuentasDao;
import unir.cajero.modelo.dao.MovimientosDao;
import unir.cajero.modelo.entity.Cuentas;


/**
 *  Controlador para gestionar todos los get y post de la pagina Transferencias
 *  
 * @author Gerard Perujo
 *
 */
@Controller
public class TransferenciaController {

	
	/**
	 * importamos la clase CuentasDao para poder utilizar los metodos que hemos creado
	 */
	@Autowired
	private CuentasDao cuedao;
	
	/**
	 * importamos la clase MovimientosDao para poder utilizar los metodos que hemos creado
	 */
	@Autowired
	private MovimientosDao movidao;
	
	
	/**
	 * Hacemos un GetMapping de /transferencias para que nos saque el HTML de la pagina transferencias
	 * 
	 * @return nos devuelve la pagina transferencias
	 */
	@GetMapping("/Transferencia")
	public String mostrarTransferencia() {
		return "Transferencia";
	}
	
	
	/**
	 *  Hacemos un post para recoger todos los datos que nos envian desde la pagina transferencias y restarle la cantidad de dinero
	 *  que nos envian a nuestra cuenta y sumarle dicha cantidad a otra cuenta que le pasamos
	 * 
	 * @param session en este parametro tendremos todas los datos de nuestro objeto cuenta con el que hemos iniciado session
	 * @param saldo la cantidad de dinero que nos envian
	 * @param idCuenta el idCuenta de la segunda cuenta donde queremos realizar la transferencia
	 * @param ratt atributo que usaremos para sacar un mensaje
	 * @return en caso de que en nuestra cuenta tengamos suficiente saldo va a realizar la transferencia a la segunda cuenta sino va a sacar
	 * un mensaje diciendo saldo insuficiente y en los 2 casos nos va a devolver a la pagina cuenta
	 */
	@PostMapping("/Transferencia")
	public String hacerTransferencia(HttpSession session, @RequestParam double saldo, @RequestParam int idCuenta, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");// a traves de un casting obtenemos los datos de la cuenta con la que hemos iniciado session que estan dentro del parametro session
		Cuentas cue2 = cuedao.buscarUna(idCuenta);// creo el objeto cue2 donde le pasaremos el id de la cuenta donde queremos hacer el ingreso
		Date fecha = new Date();
		int resultado = cuedao.extraer(cue, saldo);// creo la variable resultado para hacer una comprobacion de saldo 
		if(resultado == 0) { //si el resultado es 0 quiere decir que no hay suficiente saldo para realizar la retirada de dinero y nos va a sacar un mensaje diciendo saldo insuficiente
			ratt.addFlashAttribute("mensajeIngresoFallo", "Saldo Insuficiente no se puede retirar la cantidad");
			return "redirect:/Cuenta";// nos manda devuelta a la pagina cuentas
		}else {
			if(cue2 != null) {// en el caso de que cue2 exista vamos a realizar el ingreso sino nos saldra un mensaje de error
				cuedao.ingreso(cue2, saldo);
				session.setAttribute("cuentas", cue);//passamos todos los parametros del objeto cuenta al string cuentas para poder sacar datos del objeto en la pagina HTML
				ratt.addFlashAttribute("mensajeIngreso", "Se han Realizado la Transferencia de " + saldo + "€" + " a la cuenta: " + cue2.getIdCuenta());
				movidao.movimientoIngresoTransferencia(cue2, saldo, fecha, "");;// utilizamos el movimientoIngreso para que quede constancia del ingreso dentro de la lista de movimientos
				movidao.movimientoExtraerTransferencia(cue, saldo, fecha, "");;// utilizamos el movimientoIngreso para que quede constancia del ingreso dentro de la lista de movimientos
				return "redirect:/Cuenta";// nos devuelve a la pagina cuenta
			}else {
				ratt.addFlashAttribute("mensaje", "!!!ERROR¡¡¡ La Cuenta Introducida No Existe");
				return "redirect:/Transferencia";// nos manda devuelta a la pagina transferencia
			
			}
			
		}
	}
}
