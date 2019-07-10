package com.sysone.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysone.exception.CustomException;
import com.sysone.model.Automovil;
import com.sysone.utils.ErrorMessages;

@Component
public class AutomovilDAOImpl implements IAutomovilDAO <Automovil> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Automovil> getAll() throws Exception {
		try {
			return this.sessionFactory.getCurrentSession().createQuery("FROM com.sysone.model.Automovil", Automovil.class).list();
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_GETTING_ALL_ENTITY);
		}
	}

	@Override
	public Automovil getById(int id) throws Exception {
		Automovil automovil = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sqlString = "SELECT * FROM AUTOMOVIL WHERE ID_AUTOMOVIL = %s";
			sqlString = String.format(sqlString, id);
			Query query = session.createNativeQuery(sqlString, Automovil.class);
			automovil = (Automovil) query.getSingleResult();
		}
		catch (NoResultException ex) {
			throw new CustomException(ex.getMessage(), ErrorMessages.NO_DATA_FOUND);
		}		
		catch (Exception e) {
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_GETTING_ENTITY);
		}
		return automovil;
	}

	@Override
	public boolean save(Automovil t) throws Exception {
		return false;
	}

}
