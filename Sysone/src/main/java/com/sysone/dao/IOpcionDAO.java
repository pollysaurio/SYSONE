package com.sysone.dao;

import java.util.List;

import com.sysone.exception.CustomException;

public interface IOpcionDAO <T> {

	List<T> getOpciones(String[] codes) throws CustomException;
	
}
