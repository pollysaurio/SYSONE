package com.sysone.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dao.ITransaccionDAO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.exception.CustomException;
import com.sysone.helper.MappingHelper;
import com.sysone.model.Transaccion;
import com.sysone.utils.SessionUtil;

@Service ("ITransaccionService")
public class TransaccionServiceImpl implements ITransaccionService <TransaccionDTO> {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	private ITransaccionDAO<Transaccion> dao;
	
	public ITransaccionDAO<Transaccion> getDao() {
		return dao;
	}

	public void setDao(ITransaccionDAO<Transaccion> dao) {
		this.dao = dao;
	}
	
	@Transactional
	public List<TransaccionDTO> getAll() {
		List<TransaccionDTO> transaccionesDTO = new ArrayList<TransaccionDTO>();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			List<Transaccion> transaccionesEntities = dao.getAll();
			
			for (Transaccion transaccion : transaccionesEntities) {
				transaccionesDTO.add(MappingHelper.getInstance().entityToDTO(transaccion));
			}
			
			tx.commit();
			session.close();
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
		return transaccionesDTO;
	}

	@Transactional
	public TransaccionDTO getById(int id) {
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			Transaccion transaccion = dao.getById(id);
			transaccionDTO = MappingHelper.getInstance().entityToDTO(transaccion);
			tx.commit();
			session.close();
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
			transaccionDTO = null;
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
			transaccionDTO = null;
		}
		return transaccionDTO;
	}

	@Transactional
	public int save(TransaccionDTO transaccionDTO) {
		try {
			Transaccion transaccion = MappingHelper.getInstance().DTOtoEntity(transaccionDTO);
			dao.save(transaccion);
			return transaccion.getIdTransaccion();
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean delete(TransaccionDTO transaccionDTO) {
		try {
			Transaccion transaccion = MappingHelper.getInstance().DTOtoEntity(transaccionDTO);
			dao.delete(transaccion);
			return true;
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
