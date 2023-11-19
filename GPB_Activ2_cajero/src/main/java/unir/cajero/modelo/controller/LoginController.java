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

@Controller
public class LoginController {
	
	@Autowired
	private CuentasDao cuedao;
	
	@GetMapping("/login")
	public String loggin() {
		return "login";
	}
	

	@PostMapping("/login")
		public String inicioSesion( HttpSession session, @RequestParam int idCuenta, RedirectAttributes ratt){
			Cuentas cue = cuedao.buscarUna(idCuenta);
			
			if (cue != null) {
				session.setAttribute("cuentas", cue);
				return "redirect:/Cuenta";
			}
				ratt.addFlashAttribute("mensaje", "!!!ERROR¡¡¡ La Cuenta Introducida No Existe");
				return "redirect:/login";
			
		}

}
