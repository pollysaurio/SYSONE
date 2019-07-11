package com.sysone.helper;

import org.springframework.beans.BeanUtils;

import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.model.Automovil;
import com.sysone.model.Transaccion;

public class MappingHelper {
	
	private static MappingHelper instance = null;
	
	private MappingHelper() {}
	
	public static MappingHelper getInstance() {
		if(instance == null) {
			instance = new MappingHelper();
		}
		return instance;
	}
	
	public TransaccionDTO entityToDTO (Transaccion transaccion) {
		TransaccionDTO prestacionDTO = new TransaccionDTO();
		BeanUtils.copyProperties(transaccion, prestacionDTO);
		return prestacionDTO;
	}
	
	public Transaccion DTOtoEntity (TransaccionDTO transaccionDTO) {
		Transaccion transaccion = new Transaccion();
		BeanUtils.copyProperties(transaccionDTO, transaccion);
		return transaccion;
	}

	public AutomovilDTO entityToDTO (Automovil automovil) {
		AutomovilDTO automovilDTO = new AutomovilDTO();
		BeanUtils.copyProperties(automovil, automovilDTO);
		return automovilDTO;
	}
	
	public Automovil DTOtoEntity (AutomovilDTO automovilDTO) {
		Automovil automovil = new Automovil();
		BeanUtils.copyProperties(automovilDTO, automovil);
		return automovil;
	}

}
