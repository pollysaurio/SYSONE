package com.sysone.service;

import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;

public interface IABMService {
	
	void alta(TransaccionDTO transaccionDTO, AutomovilDTO automovilDTO, String[] codes);
	boolean baja(AutomovilDTO automovilDTO);

}
