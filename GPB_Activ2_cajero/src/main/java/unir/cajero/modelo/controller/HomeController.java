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
public class HomeController {
	
	@Autowired
	private CuentasDao cuedao;
	
	
	@GetMapping({"","/","/Home"})
	public String home() {
		return "Home";
	}
	
	
	

	
}
