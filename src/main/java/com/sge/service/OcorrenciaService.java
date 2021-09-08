package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.sge.dao.OcorrenciaDAO;
import com.sge.model.Ocorrencia;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class OcorrenciaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private OcorrenciaDAO ocorrenciaDao;
	private List<Ocorrencia> ocorrencias;
	private Ocorrencia ocorrencia = new Ocorrencia();
	@Inject
	private Ocorrencia ocorrenciaSelecionada;
	

	@Transactional
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (ocorrencia.getId() == null){
				ocorrenciaDao.save(ocorrencia);
				//System.out.println(ocorrencia.getAluno().getId());
				return "/addOcorrencias.xhtml?faces-redirect=true id="+ocorrencia.getAluno().getId();
			} else {
				ocorrenciaDao.update(ocorrencia);
				System.out.println("********** Ocorrencia Atualizada *************");
				return "/addOcorrencias.xhtml?faces-redirect=true id="+ocorrencia.getAluno().getId();
			}
			
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/addOcorrencias.xhtml?faces-redirect=true id="+ocorrencia.getAluno().getId();
		}
	}
	
	@Transactional
	public void findAll() {
		this.ocorrencias = ocorrenciaDao.findAll();
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public Ocorrencia getOcorrenciaSelecionada() {
		return ocorrenciaSelecionada;
	}

	public void setOcorrenciaSelecionada(Ocorrencia ocorrenciaSelecionada) {
		this.ocorrenciaSelecionada = ocorrenciaSelecionada;
	}
	
	public void onRowSelect(SelectEvent slc) {
		ocorrenciaSelecionada = (Ocorrencia) slc.getObject();
		ocorrencia = new Ocorrencia();
		ocorrencia = ocorrenciaSelecionada;
		System.out.print("Ocorrencia Selecionada: " + ocorrencia.getAluno().getNome());
	}
	
	@Transactional
	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ocorrenciaDao.delete(ocorrenciaSelecionada, ocorrenciaSelecionada.getId());
			context.addMessage(null, new FacesMessage("Ocorrencia Excluida"));
			System.out.print("Ocorrencia Excluida: " + ocorrencia.getAluno().getNome());
			return "/addOcorrencias.xhtml?faces-redirect=true id="+ocorrencia.getAluno().getId();
			
		} catch (Exception e) {
			// IMPLEMENTAR ERROS DO POSTGRES
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			System.out.println("Deu RUim");
			return "/addOcorrencias.xhtml?faces-redirect=true id="+ocorrencia.getAluno().getId();
			
		}

	}

}
