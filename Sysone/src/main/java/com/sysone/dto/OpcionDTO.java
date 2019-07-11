package com.sysone.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.sysone.model.Transaccion;

public class OpcionDTO {
	
	private Set<Transaccion> transacciones = new HashSet<Transaccion>();

	private int idOpcion;
	
	private String descripcion;
	
	private String code;
	
	private Date updatedDate;
	
	private int costo;

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

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
	
}
