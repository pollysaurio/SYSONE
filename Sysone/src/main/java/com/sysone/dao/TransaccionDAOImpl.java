package com.sysone.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysone.exception.CustomException;
import com.sysone.model.Transaccion;
import com.sysone.utils.ErrorMessages;

@Component
public class TransaccionDAOImpl implements ITransaccionDAO <Transaccion> {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Transaccion> getAll() throws CustomException {
		try {
			return this.sessionFactory.getCurrentSession().createQuery("FROM com.sysone.model.Transaccion", Transaccion.class).list();
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_GETTING_ALL_ENTITY);
		}
	}

	@Override
	public Transaccion getById(int id) throws CustomException {
		Transaccion transaccion = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sqlString = "SELECT * FROM TRANSACCION WHERE ID_TRANSACCION = %s";
			sqlString = String.format(sqlString, id);
			Query query = session.createNativeQuery(sqlString, Transaccion.class);
			transaccion = (Transaccion) query.getSingleResult();
		}
		catch (NoResultException ex) {
			throw new CustomException(ex.getMessage(), ErrorMessages.NO_DATA_FOUND);
		}		
		catch (Exception e) {
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_GETTING_ENTITY);
		}
		return transaccion;
	}

	@Override
	public boolean save(Transaccion transaccion) throws Exception {
		try {
			this.sessionFactory.getCurrentSession().save(transaccion);
			return true;
		} catch(Exception e){
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_SAVING_ENTITY);
		}
	}

	@Override
	public boolean delete(Transaccion transaccion) throws Exception {
		try {
			this.sessionFactory.getCurrentSession().delete(transaccion);
			return true;
		} catch(Exception e){
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_SAVING_ENTITY);
		}
	}

}
