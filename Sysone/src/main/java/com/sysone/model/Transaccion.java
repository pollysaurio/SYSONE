package com.sysone.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACCION")
public class Transaccion {
	
	public Transaccion() {}
	
	@OneToOne(mappedBy = "transaccion")
	private Automovil automovil;
	
    @ManyToMany(cascade = {	CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "TRA_OPC",
        joinColumns = @JoinColumn(name = "id_transaccion"),
        inverseJoinColumns = @JoinColumn(name = "id_opcion") )
	private Set<Opcion> opciones = new HashSet<Opcion>();
	
	@Id
	@Column(name = "id_transaccion", updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTransaccion;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "transaccion_date", nullable = false)
	private Date transaccionDate;
	
	@Column(name = "importe_a_la_fecha", nullable = false)
	private Double importe;

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

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

}
