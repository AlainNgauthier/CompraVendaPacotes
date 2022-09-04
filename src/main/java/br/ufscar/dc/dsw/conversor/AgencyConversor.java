package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Agency;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;

@Component
public class AgencyConversor implements Converter<String, Agency>{

	@Autowired
	private IAgencyService service;
	
	@Override
	public Agency convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}
