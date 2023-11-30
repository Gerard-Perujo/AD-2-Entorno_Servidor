package unir.cajero.modelo.controller;


import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import unir.cajero.modelo.entity.Movimientos;

/**
 *  Controlador para gestionar todos los get y post de la pagina Ingresar
 *  
 * @author Gerard Perujo
 *
 */
@Controller
public class IngresarController {
	
	
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
	 * Hacemos un GetMapping de /ingresar para que nos saque el HTML de la pagina ingresar
	 * 
	 * @return nos devuelve la pagina ingresar
	 */
	@GetMapping("/Ingresar")
	public String entrarIngresar() {
		return"Ingresar";
	}

	
	/**
	 *  Hacemos un post para recoger todos los datos que nos envian desde la pagina ingresar y sumarle la cantidad de dinero
	 *  que nos envian a nuestra cuenta
	 * 
	 * @param session en este parametro tendremos todas los datos de nuestro objeto cuenta con el que hemos iniciado session
	 * @param saldo la cantidad de dinero que nos envian
	 * @param ratt atributo que usaremos para sacar un mensaje
	 * @return en caso de que la cuenta exista va ha realizar el ingreso y nos devolvera a la pagina cuenta en caso de que no exista
	 * sacara un mensaje diciendo que no se ha podido realizar el ingreso y nos devolvera a la pagina cuenta
	 */
	@PostMapping("/Ingresar")
	public String ingresarDinero(HttpSession session, @RequestParam double saldo, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");// a traves de un casting obtenemos los datos de la cuenta con la que hemos iniciado session que estan dentro del parametro session
		Date fecha = new Date();
		if(cuedao.ingreso(cue, saldo)==1) {// hacemos la comprobacion de si el ingreso es == 1 se realizara la operacion en caso contrario nos sacara un mensaje de error
			session.setAttribute("cuentas", cue);//passamos todos los parametros del objeto cuenta al string cuentas para poder sacar datos del objeto en la pagina HTML
			ratt.addFlashAttribute("mensajeIngreso", "Se han ingresado " + saldo + "€" + " Su nuevo saldo es: " + cue.getSaldo());// en caso de realizar el ingreso sacamos un mensaje de comprobacion con el saldo que hemos ingresado y el saldo que tenemos ahora
			movidao.movimientoIngreso(cue, saldo, fecha, "");// utilizamos el movimientoIngreso para que quede constancia del ingreso dentro de la lista de movimientos
			return "redirect:/Cuenta";// nos envia devuelta a la cuenta
		
		}else {
			ratt.addFlashAttribute("mensajeIngresoFallo", "No se ha podido Realizar la Operacion");
			return "redirect:/Cuenta";//nos envia devuelta a la cuenta
		}
		
	}
	
	
	
	/**
	 * Hacemos un GetMapping de /extraer para que nos saque el HTML de la pagina extraer
	 * 
	 * @return nos devuelve la pagina extraer
	 */
	@GetMapping("/Extraer")
	public String entrarExtraer() {
		return "Extraer";
	}
	
	
	
	/**
	 *  Hacemos un post para recoger todos los datos que nos envian desde la pagina extraer y restarle la cantidad de dinero
	 *  que nos envian a nuestra cuenta
	 *  
	 * @param session en este parametro tendremos todos los datos de nuestro objeto cuenta con el que hemos iniciado session
	 * @param saldo la cantidad de dinero que nos envian
	 * @param ratt atributo que usaremos para sacar un mensaje
	 * @return en caso de que la cuenta exista va ha realizar una retirada y nos devolvera a la pagina cuenta en caso de que no exista
	 * sacara un mensaje diciendo que no se ha podido realizar la retirada de dinero y nos devolvera a la pagina cuenta
	 */
	@PostMapping("/Extraer")
	public String sacarDinero(HttpSession session, @RequestParam double saldo, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");// a traves de un casting obtenemos los datos de la cuenta con la que hemos iniciado session que estan dentro del parametro session
		Date fecha = new Date();
		int resultado = cuedao.extraer(cue, saldo);// creo la variable resultado para hacer una comprobacion de saldo 
		if(resultado == 0) {// si el resultado es 0 quiere decir que no hay suficiente saldo para realizar la retirada de dinero y nos va a sacar un mensaje diciendo saldo insuficiente
			ratt.addFlashAttribute("mensajeIngresoFallo", "Saldo Insuficiente no se puede retirar la cantidad");
			return "redirect:/Cuenta";//nos envia devuelta a la cuenta
		}else {
			cue = cuedao.buscarUna(cue.getIdCuenta());// en caso contrario hago un buscaruna para buscar la cuenta en cuestion y poder realizar la retirada de dinero
			session.setAttribute("cuentas", cue);//passamos todos los parametros del objeto cuenta al string cuentas para poder sacar datos del objeto en la pagina HTML
			ratt.addFlashAttribute("mensajeIngreso", "Se han Retirado " + saldo + "€" + " Su nuevo saldo es: " + cue.getSaldo());
			movidao.movimientoExtraer(cue, saldo, fecha, "");// utilizamos el movimientoIngreso para que quede constancia del ingreso dentro de la lista de movimientos
			return "redirect:/Cuenta";//nos envia devuelta a la cuenta
		}
			
		
	}
}
