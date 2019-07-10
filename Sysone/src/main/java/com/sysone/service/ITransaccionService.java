package com.sysone.service;

import java.util.List;

import com.sysone.dto.TransaccionDTO;

public interface ITransaccionService <T>{
	
	List<TransaccionDTO> getAll();
	T getById(int id);
	boolean save(T t);

}
