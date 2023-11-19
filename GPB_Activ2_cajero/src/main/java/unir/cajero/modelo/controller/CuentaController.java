package unir.cajero.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import unir.cajero.modelo.dao.CuentasDao;
import unir.cajero.modelo.entity.Cuentas;

@Controller
public class CuentaController {
	
	@Autowired
	private CuentasDao cuedao;
	
	
	@GetMapping("/Cuenta")
	public String cuenta() {
		return "Cuenta";
		
		}
	
	@GetMapping("/logout")
	public String cerrarSession(HttpSession session, Model model) {
		session.removeAttribute("cuentas");
		session.invalidate();
		model.addAttribute("mensajelogout", "La Session se ha Cerrado Correctamente");
		return "forward:/login";
	}
	

	
	
	
	

}
