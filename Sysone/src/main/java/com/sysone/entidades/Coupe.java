package com.sysone.entidades;

public class Coupe implements IAuto{

	int costo = 270000;
	
	@Override
	public int getCosto() {
		return costo;
	}

}
