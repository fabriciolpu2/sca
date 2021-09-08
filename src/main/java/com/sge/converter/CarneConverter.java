package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.CarneDAO;
import com.sge.model.Carne;


@FacesConverter(forClass = Carne.class)
public class CarneConverter implements Converter{
	
	@Inject
	private CarneDAO carneDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Carne retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = carneDao.getById(new Long(id));
		}
		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Carne) value).getId().toString();
		}
		return "";
	}
}
