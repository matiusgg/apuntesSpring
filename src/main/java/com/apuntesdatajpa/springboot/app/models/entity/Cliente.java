package com.apuntesdatajpa.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
// Paquete PERSISTENCE; que contiene las anotaciones con las que nos ayudarán a crear la tablas
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Esta clase nos ayudará a crear la tabla, por lo cual los atributos serán como los atributos de una tabla.
//Si queremos configurar que el nombre de la clase sea igual al nombre de la tabla.
//El nombre de la tabla lo ponemos en plural, ya que es una tabla, y debe ir en minuscula por buena sintaxis
//Importamso SERIALIZABLE, para que nos almacene el bean en memoria para transportarlo en otro lugar.
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	// ID del serializable,
	private static final long serialVersionUID = 1L;

	// atributo ID de la tabla
	// @GeneratedValue: Nos permite darle las propiedades del atributo de la tabla.
	// En este caso IDENTITY:Autoincrementar cada vez que haya una nueva tupla.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// si QUEREMOS CAMBIAR de que el nombre del atributo NO sea igual al nombre del
	// atributo de la tabla, usamos @Column.
	// @Column, tambien nos permite darle propiedades al atributo de la tabla como
	// cuando la estamos creando
	// por ejemplo VARCHAR; boolean, etc. Ejm: @Column(name="nombre_cliente",
	// boolean)
	// @Column(name="nombre_cliente")
	private String nombre;

	private String apellido;

	private String email;

	// Los nombre de los atributos por buena sintaxis, si estos tiene más de dos
	// palabras, entonces se separan con "_"
	// @Temporal: Nos permite definir un atributo de la tabla de tipo TIME, para
	// fechas, etc.
	// DATE: solo fecha, TIME: Hora, TIMESTAMP: fecha y hora.
	@Column(name = "create_At")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	// @PrePersist: Nos permite ingresar dentro del ciclo de vida del método PERSIST() con el objeto @Entity, el cual está
	// relacionado con el método PERSIST() del @EntityManager, donde nos permite decirle que antes
	// de que se ejecute el PERSIST() de este objeto @Entity, ejecutame este método.
	// En este caso nos permite guardar el contenido de este método en la base de datos, antes que el contenido de PERSIST().
	// En este caso, que nos ingrese una fecha antes que el resto de los valores del formulario. esto en el caso de que no queramos hacer un input de fecha.
	@PrePersist
	public void prePersist() {
		
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// TODOS ESTOS ATRIBUTOS SE VAN A MAPEAR(convertir) EN ATRIBUTOS DE LA TABLA.

}
