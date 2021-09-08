package com.sge.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.sge.dao.TurmaDAO;
import com.sge.model.Turma;

@FacesConverter(value = "turmaConverter", forClass = Turma.class)
public class TurmaConverter implements Converter{
	
	@Inject
	private TurmaDAO turmaDao;

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Turma retorno = null;
		
			if (value != null) {
				Long id = new Long(value);
				retorno = turmaDao.getById(new Long(id));
				//retorno = serieDao.getById(new Long(id));
			}
			return retorno;
	}
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		Turma retorno = null;
//		
//		
//		if (value != null) {
//			Long id = new Long(value);
//			try {
//				retorno = this.turmaDao.getById(new Long(id));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}			
//		}
//		return retorno;
//	}
	

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Turma) value).getId().toString();
		}
		return "";
	}
	
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null) {
//			Long codigo = ((Turma)value).getId();
//			String retorno = (codigo == null ? null : codigo.toString());
//			
//			return retorno;
//		}
//		return "";
//	}

}

