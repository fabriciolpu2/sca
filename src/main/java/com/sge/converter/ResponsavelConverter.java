package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.ResponsavelDAO;
import com.sge.model.Responsavel;

@FacesConverter(forClass = Responsavel.class)
public class ResponsavelConverter implements Converter{
	
	@Inject
	private ResponsavelDAO responsavelDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Responsavel retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = responsavelDao.getById(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Responsavel) value).getId().toString();
		}
		return "";
	}

}

