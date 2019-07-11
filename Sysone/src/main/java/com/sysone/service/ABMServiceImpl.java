package com.sysone.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.model.Opcion;
import com.sysone.utils.SessionUtil;

@Service ("IABMService")
public class ABMServiceImpl implements IABMService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	ITransaccionService<TransaccionDTO> tService;

	@Autowired
	IAutomovilService<AutomovilDTO> automovilService;
	
	@Autowired
	IOpcionService<Opcion> opcionService;

	@Override
	public void alta(TransaccionDTO transaccionDTO, AutomovilDTO automovilDTO) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			int idTransaccion = tService.save(transaccionDTO);
			automovilDTO.setIdTransaccion(String.valueOf(idTransaccion));
			automovilService.save(automovilDTO);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
	}

	@Override
	public void baja(TransaccionDTO transaccionDTO, AutomovilDTO automovilDTO) {
		Session session = null;
		Transaction tx = null;
		try {			
			automovilDTO = automovilService.getById(automovilDTO.getIdAutomovil());
			transaccionDTO = tService.getById(Integer.valueOf(automovilDTO.getIdTransaccion()));
			tService.delete(transaccionDTO);
			automovilService.delete(automovilDTO);
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}		
	}

}
