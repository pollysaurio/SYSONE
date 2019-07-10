package com.sysone.entidades;

public class DecoAirbag extends AutoDecorator {

	public DecoAirbag(IAuto auto) {
		super(auto);
	}

	int costo = 7000;
	
	public int getCosto() {
		return super.getCosto() + costo;
	}

}
