package com.sysone.entidades;

public class DecoABS extends AutoDecorator {

	public DecoABS(IAuto auto) {
		super(auto);
	}

	int costo = 14000;
	
	public int getCosto() {
		return super.getCosto() + costo;
	}

}
