package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.FuncionarioDAO;
import com.sge.model.Funcionario;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter{
	
	@Inject
	private FuncionarioDAO funcionarioDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = funcionarioDao.getById(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Funcionario) value).getId().toString();
		}
		return "";
	}

}

