package com.sysone.service;

import java.util.List;

public interface IOpcionService <T> {
	
	List<T> getOpciones(String[] codes);

}
