package com.sysone.service;

import java.util.List;

import com.sysone.dto.AutomovilDTO;

public interface IAutomovilService <T>{
	
	List<AutomovilDTO> getAll();
	T getById(int id);
	boolean save(T t);
	boolean delete(T t);

}
