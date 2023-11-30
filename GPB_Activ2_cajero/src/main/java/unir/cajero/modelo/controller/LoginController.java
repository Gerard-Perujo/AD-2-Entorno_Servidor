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
 *  Controlador para gestionar todos los get y post de la pagina Login
 *  
 * @author Gerard Perujo
 *
 */
@Controller
public class LoginController {
	
	/**
	 * importamos la clase CuentasDao para poder utilizar los metodos que hemos creado
	 */
	@Autowired
	private CuentasDao cuedao;
	
	
	/**
	 * Hacemos un GetMapping de /login para que nos saque el HTML de la pagina login
	 * 
	 * @return nos devuelve la pagina login
	 */
	@GetMapping("/login")
	public String loggin() {
		return "login";
	}
	

	/**
	 * Hacemos un post para recoger todos los datos que nos envian desde la pagina login y poder comprobar si el
	 * usuario existe en la BBDD
	 * 
	 * @param session este parametro va a recoger el objeto cuenta y lo va a guardar dentro del parametro session
	 * @param idCuenta el id de la cuenta que nos entra
	 * @param ratt atributo que usaremos para sacar un mensaje
	 * @return va a comprobar si la cuenta existe en la BBDD en caso de que exista nos va a reenviar a la pagina
	 * cuenta en caso de que no exista la cuenta devolvera un mensaje de error
	 */
	@PostMapping("/login")
		public String inicioSesion( HttpSession session, @RequestParam int idCuenta, RedirectAttributes ratt){
			Cuentas cue = cuedao.buscarUna(idCuenta);// utilizamos el buscar una para buscar el id que nos pasan en la BBDD
			
			if (cue != null) {// comprobamos si la cuenta existe o no en caso de que no exista sacaremos un mensaje de error
				session.setAttribute("cuentas", cue);// se agrega la cuenta en el parametro session 
				return "redirect:/Cuenta";// nos envia a la pagina cuenta
			}
				ratt.addFlashAttribute("mensaje", "!!!ERROR¡¡¡ La Cuenta Introducida No Existe");// mete el mensaje de error en el objeto mensaje
				return "redirect:/login";// nos envia de vuelta a la pagina login donde sacar el mensaje de error
			
		}

}
