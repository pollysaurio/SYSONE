package com.sysone.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dao.IAutomovilDAO;
import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.exception.CustomException;
import com.sysone.model.Automovil;
import com.sysone.model.Transaccion;
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
	
	private AutomovilDTO entityToDTO (Automovil automovil) {
		AutomovilDTO automovilDTO = new AutomovilDTO();
		BeanUtils.copyProperties(automovil, automovilDTO);
		return automovilDTO;
	}
	
	private Automovil DTOtoEntity (AutomovilDTO automovilDTO) {
		Automovil automovil = new Automovil();
		BeanUtils.copyProperties(automovilDTO, automovil);
		return automovil;
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
				automovilesDTO.add(entityToDTO(automovil));
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
			automovilDTO = entityToDTO(automovil);
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
			return dao.save(DTOtoEntity(automovilDTO));
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	}
	
	
	
}
