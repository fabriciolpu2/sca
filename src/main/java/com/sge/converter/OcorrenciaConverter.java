package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.OcorrenciaDAO;
import com.sge.model.Ocorrencia;

@FacesConverter(forClass = Ocorrencia.class)
public class OcorrenciaConverter implements Converter{
	
	@Inject
	private OcorrenciaDAO ocorrenciaDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ocorrencia retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = ocorrenciaDao.getById(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Ocorrencia) value).getId().toString();
		}
		return "";
	}

}

