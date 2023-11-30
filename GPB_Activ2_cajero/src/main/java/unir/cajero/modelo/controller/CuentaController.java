package unir.cajero.modelo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




import jakarta.servlet.http.HttpSession;
;


/**
 *  * Controlador para gestionar todos los get y post de la pagina cuenta
 * @author Gerard Perujo
 *
 */
@Controller
public class CuentaController {
	
	

	/**
	 *  Hacemos un GetMapping de /cuenta para que nos saque el HTML de la pagina cuenta
	 *  
	 * @return nos devuelve la pagina cuenta
	 */
	@GetMapping("/Cuenta")
	public String cuenta() {
		return "Cuenta";
		
		}
	
	
	/**
	 * Metodo logout para poder cerrar la session cuando queramos salir
	 * 
	 * @param session en este parametro estan todos los datos de la cuenta con la que nos hemos hecho login
	 * @param model usaremos este atributo para sacar un mensaje en la pantalla
	 * @return una vez echo logout nos va a sacar un mensaje diciendo que la session se ha cerrado y nos mandara
	 * a la pagina login
	 */
	@GetMapping("/logout")
	public String cerrarSession(HttpSession session, Model model) {
		session.removeAttribute("cuentas");
		session.invalidate();
		model.addAttribute("mensajelogout", "La Session se ha Cerrado Correctamente");
		return "forward:/login";
	}
	

	
	
	
	

}
