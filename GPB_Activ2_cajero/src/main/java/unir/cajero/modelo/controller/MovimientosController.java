package unir.cajero.modelo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import jakarta.servlet.http.HttpSession;
import unir.cajero.modelo.dao.MovimientosDao;
import unir.cajero.modelo.entity.Cuentas;
import unir.cajero.modelo.entity.Movimientos;

@Controller
public class MovimientosController {
	
	@Autowired
	private MovimientosDao moviDao;
	
	@GetMapping("/Movimientos")
	public String mostrarMovimientos(Model model) {
		List<Movimientos> movi = moviDao.sacarTodo();
		model.addAttribute("movimientos", movi);		
		return "Movimientos";
	}
	
	
}
