package com.sysone.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dao.IAutomovilDAO;
import com.sysone.dto.AutomovilDTO;
import com.sysone.exception.CustomException;
import com.sysone.helper.MappingHelper;
import com.sysone.model.Automovil;
import com.sysone.utils.SessionUtil;

@Service ("IAutomovilService")
public class AutomovilServiceImpl implements IAutomovilService<AutomovilDTO>{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private IAutomovilDAO<Automovil> dao;

	public IAutomovilDAO<Automovil> getDao() {
		return dao;
	}

	public void setDao(IAutomovilDAO<Automovil> dao) {
		this.dao = dao;
	}

	@Override
	public List<AutomovilDTO> getAll() {
		List<AutomovilDTO> automovilesDTO = new ArrayList<AutomovilDTO>();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			List<Automovil> automovilesEntities = dao.getAll();
			
			for (Automovil automovil : automovilesEntities) {
				automovilesDTO.add(MappingHelper.getInstance().entityToDTO(automovil));
			}
			
			tx.commit();
			session.close();
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
		return automovilesDTO;
	}

	@Override
	public AutomovilDTO getById(int id) {
		AutomovilDTO automovilDTO = new AutomovilDTO();
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			Automovil automovil = dao.getById(id);
			automovilDTO = MappingHelper.getInstance().entityToDTO(automovil);
			tx.commit();
			session.close();
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
			automovilDTO = null;
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
			automovilDTO = null;
		}
		return automovilDTO;
	}

	@Override
	public boolean save(AutomovilDTO automovilDTO) {
		try {
			dao.save(MappingHelper.getInstance().DTOtoEntity(automovilDTO));
			return true;
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(AutomovilDTO automovilDTO) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();

			dao.delete(MappingHelper.getInstance().DTOtoEntity(automovilDTO));

			tx.commit();
			session.close();
			return true;
		} catch (CustomException e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
		return false;
	}
	
}
