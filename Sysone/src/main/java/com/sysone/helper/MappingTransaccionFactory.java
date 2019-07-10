package com.sysone.helper;

import com.sysone.entidades.AutoDecorator;
import com.sysone.entidades.Coupe;
import com.sysone.entidades.DecoABS;
import com.sysone.entidades.DecoAirbag;
import com.sysone.entidades.DecoAireAcond;
import com.sysone.entidades.DecoLlantaAleacion;
import com.sysone.entidades.DecoTechoCorredizo;
import com.sysone.entidades.Familiar;
import com.sysone.entidades.IAuto;
import com.sysone.entidades.Sedan;
import com.sysone.utils.Constants;

public class MappingTransaccionFactory {

	private static MappingTransaccionFactory instance = null;
	private MappingTransaccionFactory() {}
	
	public static MappingTransaccionFactory getInstance() {
		if(instance == null) {
			instance = new MappingTransaccionFactory();
		}
		return instance;
	}
	
	public IAuto getAuto(String modelo) {
		if (Constants.AUTO_MODELO_COUPE.equalsIgnoreCase(modelo)) {
			return new Coupe();
		} else if (Constants.AUTO_MODELO_FAMILIAR.equalsIgnoreCase(modelo)) {
			return new Familiar();
		} else if (Constants.AUTO_MODELO_SEDAN.equalsIgnoreCase(modelo)) {
			return new Sedan();
		} else {
			return null;
		}
	}
	
	public AutoDecorator getDecorator(String code, IAuto auto) {
		if (Constants.DECO_TC.equalsIgnoreCase(code)) {
			return new DecoTechoCorredizo(auto);
		} else if (Constants.DECO_AA.equalsIgnoreCase(code)) {
			return new DecoAireAcond(auto);
		} else if (Constants.DECO_ABS.equalsIgnoreCase(code)) {
			return new DecoABS(auto);
		} else if (Constants.DECO_AB.equalsIgnoreCase(code)) {
			return new DecoAirbag(auto);
		} else if (Constants.DECO_LL.equalsIgnoreCase(code)) {
			return new DecoLlantaAleacion(auto);
		} else {
			return null;
		}
	}
	
}
