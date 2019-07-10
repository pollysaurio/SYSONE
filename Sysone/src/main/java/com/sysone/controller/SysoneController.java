package com.sysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sysone.dto.AutomovilDTO;
import com.sysone.dto.TransaccionDTO;
import com.sysone.entidades.IAuto;
import com.sysone.helper.MappingJsonTransaccion;
import com.sysone.helper.MappingTransaccionFactory;
import com.sysone.service.IAutomovilService;
import com.sysone.service.ITransaccionService;

@RestController
public class SysoneController {

	@Autowired
	ITransaccionService<TransaccionDTO> tService;

	@Autowired
	IAutomovilService<AutomovilDTO> tAutomovilService;

	MappingJsonTransaccion mapper = new MappingJsonTransaccion();
	
	@RequestMapping(value = "/transaccion/calculate", method = RequestMethod.GET, headers = "Accept=application/json")
	public String calculate (@RequestBody String data) {
		tAutomovilService.getAll();
		List<String> decoList = mapper.getDecoList(data);
		String modelo = mapper.getModelo(data);
		IAuto auto = MappingTransaccionFactory.getInstance().getAuto(modelo);
		
		for (String code : decoList) {
			IAuto decorador = MappingTransaccionFactory.getInstance().getDecorator(code, auto);
			auto = decorador;
		}
		
		int costo = auto.getCosto();
		
		return String.valueOf(costo);
	}
	
	@RequestMapping(value = "/transaccion/add", method = RequestMethod.GET, headers = "Accept=application/json")
	public String addAuto (@RequestBody String data) {		
		List<String> decoList = mapper.getDecoList(data);
		String modelo = mapper.getModelo(data);
		IAuto auto = MappingTransaccionFactory.getInstance().getAuto(modelo);
		
		for (String code : decoList) {
			IAuto decorador = MappingTransaccionFactory.getInstance().getDecorator(code, auto);
			auto = decorador;
		}
		
		int costo = auto.getCosto();
		
		return String.valueOf(costo);
	}
	
	@RequestMapping(value = "/transaccion/showAll", method = RequestMethod.GET, headers = "Accept=application/json")
	public String listAllTransacciones (@RequestBody String data) {
		String json = "";
		List<TransaccionDTO> transacciones = tService.getAll();
		
		if (transacciones != null && transacciones.size() > 0) {
			json = mapper.mapEntityListToJson(transacciones);
		}
		
		return json;
	}
	
	@RequestMapping(value = "/transaccion/showById/{idTransaccion}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getTransaccion (@PathVariable(value = "idTransaccion") String idTransaccion) {
		TransaccionDTO transaccionDTO = null;
		String json = "";
		try {
			int id = Integer.parseInt(idTransaccion);
			transaccionDTO = tService.getById(id);

			if (transaccionDTO != null) {
				json = mapper.mapEntityToJson(transaccionDTO);
			}
			
		} catch (NumberFormatException e) {
			
		}

		return json;
	}
	
}
