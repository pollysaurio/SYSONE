package com.sysone.dao;

import java.util.List;

public interface ITransaccionDAO <T> {
	
	List<T> getAll() throws Exception;
	T getById(int id) throws Exception;
	boolean save(T t) throws Exception;
	boolean delete(T t) throws Exception;

}
