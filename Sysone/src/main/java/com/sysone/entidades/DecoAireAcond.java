package com.sysone.entidades;

public class DecoAireAcond extends AutoDecorator {

	public DecoAireAcond(IAuto auto) {
		super(auto);
	}

	int costo = 20000;
	
	public int getCosto() {
		return super.getCosto() + costo;
	}

}
