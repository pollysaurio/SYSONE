package com.sysone.entidades;

public abstract class AutoDecorator implements IAuto{

	int costo = 0;
	private IAuto auto;
	
	public AutoDecorator(IAuto auto) {
		this.auto = auto;
	}
	
	@Override
	public int getCosto() {
		return auto.getCosto();
	}
	
}
