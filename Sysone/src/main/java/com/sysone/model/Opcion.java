package com.sysone.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OPCION")
public class Opcion {

	public Opcion () {}
	
	@ManyToMany(mappedBy = "opciones")
	private Set<Transaccion> transacciones = new HashSet<Transaccion>();
	
	@Id
	@Column(name = "id_opcion", updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOpcion;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "code", nullable = false)
	private String code;	
	
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	@Column(name = "costo", nullable = false)
	private int costo;

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
}
