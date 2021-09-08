package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.SerieDAO;
import com.sge.model.Serie;

@FacesConverter(forClass = Serie.class)
public class SerieConverter implements Converter{
	
	@Inject
	private SerieDAO serieDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Serie retorno = null;
		
			if (value != null) {
				Long id = new Long(value);
				retorno = serieDao.getById(new Long(id));
			}
			return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Serie) value).getId().toString();
		}
		return "";
	}

}

