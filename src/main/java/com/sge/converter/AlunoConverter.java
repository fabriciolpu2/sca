package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.AlunoDAO;
import com.sge.model.Aluno;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter{
	
	@Inject
	private AlunoDAO alunoDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = alunoDao.getById(new Long(id));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Aluno) value).getId().toString();
		}
		return "";
	}

}

