package com.sysone.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysone.exception.CustomException;
import com.sysone.model.Opcion;
import com.sysone.utils.ErrorMessages;

@Component
public class OpcionDAOImpl implements IOpcionDAO <Opcion>  {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Opcion> getOpciones(String[] codes) throws CustomException {
		List<Opcion> opciones = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			String sqlString = "SELECT * FROM OPCION WHERE CODE IN (%s)";
			sqlString = String.format(sqlString, "'" + String.join("','", codes) + "'");
			Query query = session.createNativeQuery(sqlString, Opcion.class);
			opciones = (List<Opcion>) query.getResultList();
		}
		catch (NoResultException ex) {
			throw new CustomException(ex.getMessage(), ErrorMessages.NO_DATA_FOUND);
		}		
		catch (Exception e) {
			throw new CustomException(e.getMessage(), ErrorMessages.ERROR_GETTING_ENTITY);
		}
		return opciones;
	}

}
