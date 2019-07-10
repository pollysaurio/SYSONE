package com.sysone.dao;

import java.util.List;

public interface IAutomovilDAO <T> {
	
	List<T> getAll() throws Exception;
	T getById(int id) throws Exception;
	boolean save(T t) throws Exception;

}
