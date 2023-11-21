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

@Controller
public class TransferenciaController {

	@Autowired
	private CuentasDao cuedao;
	@Autowired
	private MovimientosDao movidao;
	
	
	@GetMapping("/Transferencia")
	public String mostrarTransferencia() {
		return "Transferencia";
	}
	
	@PostMapping("/Transferencia")
	public String hacerTransferencia(HttpSession session, @RequestParam double saldo, @RequestParam int idCuenta, RedirectAttributes ratt) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");
		Cuentas cue2 = cuedao.buscarUna(idCuenta);
		Date fecha = new Date();
		int resultado = cuedao.extraer(cue, saldo);
		if(resultado == 0) {
			ratt.addFlashAttribute("mensajeIngresoFallo", "Saldo Insuficiente no se puede retirar la cantidad");
			return "redirect:/Cuenta";
		}else {
			if(cue2 != null) {
				cuedao.ingreso(cue2, saldo);
				session.setAttribute("cuentas", cue);
				ratt.addFlashAttribute("mensajeIngreso", "Se han Realizado la Transferencia de " + saldo + "€" + " a la cuenta: " + cue2.getIdCuenta());
				movidao.movimientoIngreso(cue2, saldo, fecha, "");
				movidao.movimientoExtraer(cue, saldo, fecha, "");
				return "redirect:/Cuenta";
			}else {
				ratt.addFlashAttribute("mensaje", "!!!ERROR¡¡¡ La Cuenta Introducida No Existe");
				return "redirect:/Transferencia";
			
			}
			
		}
	}
}
