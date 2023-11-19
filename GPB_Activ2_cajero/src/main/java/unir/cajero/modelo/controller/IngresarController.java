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
import unir.cajero.modelo.entity.Movimientos;


@Controller
public class IngresarController {
	
	@Autowired
	private CuentasDao cuedao;
	
	@GetMapping("/Ingresar")
	public String entrarIngresar() {
		return"Ingresar";
	}

	
	@PostMapping("/Ingresar")
	public String ingresarDinero(HttpSession session, @RequestParam double saldo, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");
		if(cuedao.ingreso(cue, saldo)==1) {
			cue = cuedao.buscarUna(cue.getIdCuenta());
			session.setAttribute("cuentas", cue);
			ratt.addFlashAttribute("mensajeIngreso", "Se han ingresado " + saldo + "€" + " Su nuevo saldo es: " + cue.getSaldo());
			return "redirect:/Cuenta";
		
		}else {
			ratt.addFlashAttribute("mensajeIngresoFallo", "No se ha podido Realizar la Operacion");
			return "redirect:/Cuenta";
		}
		
	}
	
	@GetMapping("/Extraer")
	public String entrarExtraer() {
		return "Extraer";
	}
	
	@PostMapping("/Extraer")
	public String sacarDinero(HttpSession session, @RequestParam double saldo, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");
		int resultado = cuedao.extraer(cue, saldo);
		if(resultado == 0) {
			ratt.addFlashAttribute("mensajeIngresoFallo", "Saldo Insuficiente no se puede retirar la cantidad");
			return "redirect:/Cuenta";
		}else {
			cue = cuedao.buscarUna(cue.getIdCuenta());
			session.setAttribute("cuentas", cue);
			ratt.addFlashAttribute("mensajeIngreso", "Se han Retirado " + saldo + "€" + " Su nuevo saldo es: " + cue.getSaldo());
			return "redirect:/Cuenta";
		}
			
		
	}
}
