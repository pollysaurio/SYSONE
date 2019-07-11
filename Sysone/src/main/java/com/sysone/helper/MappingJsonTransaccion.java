package com.sysone.helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sysone.dto.TransaccionDTO;
import com.sysone.model.Opcion;
import com.sysone.utils.Constants;

public class MappingJsonTransaccion {
	
	public String mapEntityToJson (TransaccionDTO transaccionDTO) {
		
		JSONObject json = new JSONObject();
		JSONObject jsonDetalle = new JSONObject();
		jsonDetalle.put(Constants.JSON_ID, transaccionDTO.getIdTransaccion());
		jsonDetalle.put(Constants.JSON_TIPO, transaccionDTO.getTipo());
		jsonDetalle.put(Constants.JSON_FECHA, transaccionDTO.getTransaccionDate());
		jsonDetalle.put(Constants.JSON_IMPORTE, transaccionDTO.getImporte());
		
		String opciones = addOpciones(transaccionDTO, jsonDetalle);
		if(!"".equals(opciones))
			jsonDetalle.put(Constants.JSON_OPCIONES, opciones);
		
	    json.put(Constants.JSON_TRANSACCION, jsonDetalle);
	    
	    return json.toString();
	}

	public String mapEntityListToJson (List<TransaccionDTO> transaccionesDTO) {
		JSONObject json = new JSONObject();
		JSONObject jsonDetalle;
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		for (TransaccionDTO transaccionDTO : transaccionesDTO) {
			jsonDetalle = new JSONObject();
			jsonDetalle.put(Constants.JSON_ID, transaccionDTO.getIdTransaccion());
			jsonDetalle.put(Constants.JSON_TIPO, transaccionDTO.getTipo());
			jsonDetalle.put(Constants.JSON_FECHA, transaccionDTO.getTransaccionDate());
			jsonDetalle.put(Constants.JSON_IMPORTE, transaccionDTO.getImporte());
			
			String opciones = addOpciones(transaccionDTO, jsonDetalle);
			if(!"".equals(opciones))
				jsonDetalle.put(Constants.JSON_OPCIONES, opciones);

			list.add(jsonDetalle);
		}
		
		json.put(Constants.JSON_TRANSACCIONES, list);
	    return json.toString();
	}
	
	private String addOpciones(TransaccionDTO transaccionDTO, JSONObject jsonDetalle) {
		StringBuilder sbOpciones = new StringBuilder();
		boolean separate = false;
		for (Opcion opcion : transaccionDTO.getOpciones()) {
			if(separate) {
				sbOpciones.append(Constants.JSON_SEPARATOR);
			}
			sbOpciones.append(opcion.getCode());
			separate = true;
		}
		return sbOpciones.toString().trim();
	}

	public List<String> getDecoList(String data) {
		try {
			List<String> list = new ArrayList<String>();
			JSONObject jsonObject =  new JSONObject(data);
			JSONArray jsonArray = (JSONArray) jsonObject.get(Constants.JSON_PARAM_OPCION);
			for (int i=0;i<jsonArray.length();i++){ 
				list.add(jsonArray.getString(i));
			} 
			return list;
		}catch (Exception e){
			return null;
		}
	}

	public String getModelo(String data) {
		try {
			JSONObject jsonObject =  new JSONObject(data);
			return (String) jsonObject.get(Constants.JSON_PARAM_MODELO);
		}catch (Exception e){
			return null;
		}
	}
		
}
