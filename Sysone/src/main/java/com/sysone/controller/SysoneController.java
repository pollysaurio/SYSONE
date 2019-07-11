package com.sysone.controller;

import java.sql.Date;
import java.util.HashSet;
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
import com.sysone.model.Opcion;
import com.sysone.service.IABMService;
import com.sysone.service.IAutomovilService;
import com.sysone.service.IOpcionService;
import com.sysone.service.ITransaccionService;
import com.sysone.utils.Constants;

@RestController
public class SysoneController {

	@Autowired
	ITransaccionService<TransaccionDTO> tService;

	@Autowired
	IAutomovilService<AutomovilDTO> automovilService;
	
	@Autowired
	IOpcionService<Opcion> opcionService;
	
	@Autowired
	IABMService abmService;
	
	MappingJsonTransaccion mapper = new MappingJsonTransaccion();
	
	@RequestMapping(value = "/transaccion/calculate", method = RequestMethod.GET, headers = "Accept=application/json")
	public String calculate (@RequestBody String data) {
		List<String> decoList = mapper.getDecoList(data);
		String modelo = mapper.getModelo(data);
		IAuto auto = MappingTransaccionFactory.getInstance().getAuto(modelo);
		
		for (String code : decoList) {
			IAuto decorador = MappingTransaccionFactory.getInstance().getDecorator(code, auto);
			auto = decorador;
		}
		
		int costo = auto.getCosto();
		return "El costo del vehiculo es de: " + String.valueOf(costo);
	}
	
	@RequestMapping(value = "/transaccion/ABM/baja/{idAuto}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String baja (@PathVariable(value = "idAuto") String idAuto) {
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		AutomovilDTO automovilDTO = new AutomovilDTO();
		String json = "";
		try {
			int id = Integer.parseInt(idAuto);
			automovilDTO.setIdAutomovil(id);
			abmService.baja(transaccionDTO, automovilDTO);
			json = "Baja Correcta";
		} catch (NumberFormatException e) {
			json = "Error en la baja";
		}

		return json;
	}

	
	@RequestMapping(value = "/transaccion/ABM/transaccion/{tipo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String transaccionAuto (@RequestBody String data, @PathVariable(value = "tipo") String tipo) {		
		List<String> decoList = mapper.getDecoList(data);
		String[] codes = new String[decoList.size()];	
		codes = decoList.toArray(codes);
		String modelo = mapper.getModelo(data);
		IAuto auto = MappingTransaccionFactory.getInstance().getAuto(modelo);
		
		for (String code : decoList) {
			IAuto decorador = MappingTransaccionFactory.getInstance().getDecorator(code, auto);
			auto = decorador;
		}
		
		AutomovilDTO automovilDTO = new AutomovilDTO();
		TransaccionDTO transaccionDTO = new TransaccionDTO();

		if(Constants.TIPO_ALTA.equalsIgnoreCase(tipo)) {
			transaccionDTO.setTipo(Constants.TIPO_ALTA);
		} else if (Constants.TIPO_CONSULTA.equalsIgnoreCase(tipo)) {
			transaccionDTO.setTipo(Constants.TIPO_CONSULTA);
		} else {
			return "Tipo de operacion desconocido, indique si es ALTA o CONSULTA";
		}
		
		int costo = auto.getCosto();
		transaccionDTO.setImporte(Double.valueOf(auto.getCosto()));
		transaccionDTO.setTransaccionDate(new Date(System.currentTimeMillis()));
		List<Opcion> opciones = opcionService.getOpciones(codes);
		transaccionDTO.setOpciones(new HashSet<Opcion>(opciones));
		automovilDTO.setModelo(modelo);
		
		abmService.alta(transaccionDTO, automovilDTO);
		
		return "El costo del vehiculo es de: " + String.valueOf(costo);
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
