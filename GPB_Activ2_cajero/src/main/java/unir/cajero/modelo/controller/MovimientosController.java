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

/**
 *  Controlador para gestionar todos los get y post de la pagina Movimientos
 *  
 * @author Gerard Perujo
 *
 */
@Controller
public class MovimientosController {
	
	
	/**
	 * importamos la clase MovimientosDao para poder utilizar los metodos que hemos creado
	 */
	@Autowired
	private MovimientosDao moviDao;
	
	
	
	/**
	 * Hacemos un get para que nos saque todos los movimientos que se han realizado en nuestra cuenta 
	 * 
	 * @param session en este parametro tendremos todas los datos de nuestro objeto cuenta con el que hemos iniciado session
	 * @param model en este parametro podremos sacar nuestro objeto movimiento con todos nuestro movimientos atraves de un string para plasmarlo en el HTML
	 * @return nos devuelve todos los movimientos realizados en nuestra cuenta 
	 */
	@GetMapping("/Movimientos")
	public String mostrarMovimientos(HttpSession session, Model model) {
		Cuentas cue = (Cuentas) session.getAttribute("cuentas");// a traves de un casting obtenemos los datos de la cuenta con la que hemos iniciado session que estan dentro del parametro session
		int idCuenta = cue.getIdCuenta();//metemos la id de la cuenta en la variable idcuenta
		List<Movimientos> movi = moviDao.sacarTodo(idCuenta);// sacamos una lista con todos los mnovimientos pasandole la id de la cuenta
		model.addAttribute("movimientos", movi);// sacamos el objeto movimientos con todos sus datos atraves de un string		
		return "Movimientos";// nos devuelve a la pagina movimientos
	}
	
	/**
	 * Este metodo es para poder trabajar con las fechas y darle un formato con el cual queremos
	 * que salga impreso en nuestro HTML
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
