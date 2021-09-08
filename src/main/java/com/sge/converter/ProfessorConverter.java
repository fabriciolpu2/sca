package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.ProfessorDAO;
import com.sge.model.Professor;

// 
@FacesConverter(value = "professorConverter", forClass = Professor.class)
public class ProfessorConverter implements Converter{
	
	@Inject
	private ProfessorDAO professorDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;
		
		
		if (value != null) {
			Long id = new Long(value);
			try {
				retorno = this.professorDao.getById(new Long(id));
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Professor)value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		return "";
	}

}

