package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.EnderecoDAO;
import com.sge.model.Endereco;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter{
	
	@Inject
	private EnderecoDAO enderecoDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = enderecoDao.getById(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Endereco) value).getId().toString();
		}
		return "";
	}
	
}

