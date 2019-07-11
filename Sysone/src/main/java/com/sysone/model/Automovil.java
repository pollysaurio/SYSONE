package com.sysone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOMOVIL")
public class Automovil {

	public Automovil () {}
		
	@Id
	@Column(name = "ID_AUTOMOVIL", updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAutomovil;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;

	@Column(name = "ID_TRANSACCION", nullable = false)
	private String idTransaccion;

	public int getIdAutomovil() {
		return idAutomovil;
	}

	public void setIdAutomovil(int idAutomovil) {
		this.idAutomovil = idAutomovil;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
}
