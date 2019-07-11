package com.sysone.service;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.helper.MappingHelper;
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
	public void alta(TransaccionDTO transaccionDTO, AutomovilDTO automovilDTO, String[] codes) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			
			List<Opcion> opciones = opcionService.getOpciones(codes);
			transaccionDTO.setOpciones(new HashSet<Opcion>(opciones));
			
			automovilDTO.setTransaccion(MappingHelper.getInstance().DTOtoEntity(transaccionDTO));
			automovilService.save(automovilDTO);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			SessionUtil.rollbackTransaction(session, tx);
			e.printStackTrace();
		}
	}

	@Override
	public boolean baja(AutomovilDTO automovilDTO) {
		try {			
			automovilDTO = automovilService.getById(automovilDTO.getIdAutomovil());
			automovilService.delete(automovilDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
