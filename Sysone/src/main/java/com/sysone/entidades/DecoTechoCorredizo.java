package com.sysone.entidades;

public class DecoTechoCorredizo extends AutoDecorator {

	public DecoTechoCorredizo(IAuto auto) {
		super(auto);
	}

	int costo = 12000;
	
	public int getCosto() {
		return super.getCosto() + costo;
	}
	
}
