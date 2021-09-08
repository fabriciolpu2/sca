package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.sge.dao.ResponsavelDAO;
import com.sge.model.Responsavel;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class ResponsavelService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ResponsavelDAO responsavelDao;
	private List<Responsavel> responsaveis;
	
	@Inject
	private Responsavel responsavel;

	@Transactional
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			responsavelDao.save(responsavel);
			context.addMessage(null, new FacesMessage(
					"Responsavel salvo com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	@Transactional
	public void findAll() {
		this.responsaveis = responsavelDao.findAll();
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}
}
