package com.sysone.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dao.IOpcionDAO;
import com.sysone.exception.CustomException;
import com.sysone.model.Opcion;
import com.sysone.utils.SessionUtil;

@Service ("IOpcionService")
public class OpcionServiceImpl implements IOpcionService <Opcion> {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	private IOpcionDAO<Opcion> dao;
	
	public IOpcionDAO<Opcion> getDao() {
		return dao;
	}

	public void setDao(IOpcionDAO<Opcion> dao) {
		this.dao = dao;
	}

	@Override
	public List<Opcion> getOpciones(String[] codes) {
		List<Opcion> opciones = new ArrayList<Opcion>();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			opciones = dao.getOpciones(codes);
			tx.commit();
			session.close();
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
		return opciones;
	}
	
//	private OpcionDTO entityToDTO (Opcion opcion) {
//		OpcionDTO opcionDTO = new OpcionDTO();
//		BeanUtils.copyProperties(opcion, opcionDTO);
//		return opcionDTO;
//	}

}




