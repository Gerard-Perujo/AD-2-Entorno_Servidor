package unir.cajero.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import unir.cajero.modelo.dao.CuentasDao;
import unir.cajero.modelo.entity.Cuentas;

/**
 * Controlador para gestionar todos los get y post de la pagina Home
 * 
 * @author Gerard Perujo
 *
 */
@Controller
public class HomeController {
	
	
	/**
	 * Hacemos un GetMapping de /home para que nos saque el HTML de la pagina home
	 * 
	 * @return nos devuelve la pagina home
	 */
	@GetMapping({"","/","/Home"})
	public String home() {
		return "Home";
	}
	
	
	

	
}
