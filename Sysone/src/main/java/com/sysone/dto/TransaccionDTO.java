package com.sysone.dto;

import java.sql.Date;
import java.util.Set;

import com.sysone.model.Opcion;

public class TransaccionDTO {
	
	private int idTransaccion;
	
	private String tipo;
	
	private Date transaccionDate;
	
	private Double importe;
	
	private Set<Opcion> opciones;

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getTransaccionDate() {
		return transaccionDate;
	}

	public void setTransaccionDate(Date transaccionDate) {
		this.transaccionDate = transaccionDate;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Set<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(Set<Opcion> opciones) {
		this.opciones = opciones;
	}

}
