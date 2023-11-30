package unir.cajero.modelo.entity;

import java.text.SimpleDateFormat;


import java.util.Date;

import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Me creo la clase Cuentas donde relaciono la tabla a la que hace referencia esta clase en la BBDD y 
 * luego tambien relaciono cada variable con su columna de la tabla de la BBDD en caso de que 
 * el nombre en la BBDD fuera diferente que el de la variable.
 * 
 * para generar los constructores, getters, and setters utulizo el lombok 
 * @author Gerard Perujo
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="movimientos")//es el nombre de la tabla a la que hace referencia en la BBDD
public class Movimientos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_movimiento")// es el nombre de la columna a la que hace referencia en la BBDD
	private int idMovimiento;
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)// declaramos de que tipo es la variable fecha
	private Date fecha;
	private double cantidad;
	private String operacion;
	@ManyToOne
	@JoinColumn(name="id_cuenta")// es el nombre de la columna a la que hace referencia en la BBDD
	private Cuentas cuenta;

}
