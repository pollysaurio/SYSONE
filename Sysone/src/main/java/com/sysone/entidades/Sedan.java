package com.sysone.entidades;

public class Sedan implements IAuto{

	int costo = 230000;
	
	@Override
	public int getCosto() {
		return costo;
	}

}
