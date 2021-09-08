package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.UsuarioDAO;
import com.sge.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{
	
	@Inject
	private UsuarioDAO usuarioDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
			if (value != null) {
				Long id = new Long(value);
				retorno = usuarioDao.getById(new Long(id));
			}
			return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Usuario) value).getId().toString();
		}
		return "";
	}

}