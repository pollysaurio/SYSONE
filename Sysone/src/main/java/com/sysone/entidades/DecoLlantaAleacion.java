package com.sysone.entidades;

public class DecoLlantaAleacion extends AutoDecorator {

	public DecoLlantaAleacion(IAuto auto) {
		super(auto);
	}

	int costo = 12000;
	
	public int getCosto() {
		return super.getCosto() + costo;
	}
	
}
