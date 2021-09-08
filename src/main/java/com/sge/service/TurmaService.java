package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.sge.dao.TurmaDAO;
import com.sge.model.Turma;
import com.sge.model.Turno;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class TurmaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaDAO TurmaDao;
	private List<Turma> Turmas;
	private Turma turma = new Turma();
	private Turma turmaSelecionada = new Turma();
	
	
	@Transactional
	public String salvar() {
		
		
		System.out.println("Prof Selecionada: " + turma.getProfessores());
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (turma.getId() == null) {
		
				TurmaDao.save(turma);
				context.addMessage(null, new FacesMessage(
						"Turma salva com sucesso!"));
				return "/cadastrarTurma?faces-redirect=true";
			} else {
				TurmaDao.update(turma);
				context.addMessage(null, new FacesMessage("Turma Atualizada"));
				return "/cadastrarTurma?faces-redirect=true";
			}			
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/cadastrarTurma?faces-redirect=true";
		}
	}

	
	@Transactional
	public void findAll() {
		this.Turmas = TurmaDao.findAll();
	}

	
	public List<Turma> getTurmas() {
		return Turmas;
	}

	public Turma getTurma() {
		return turma;
	}
	
	public Turno[] getTurnos(){
		return Turno.values();
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}
	
	public void onRowSelect (SelectEvent slc) {
		turmaSelecionada = (Turma) slc.getObject();
		turma = new Turma();
		turma = turmaSelecionada;
		turmaSelecionada.setProfessores(null);
		System.out.println("Turma Selecionada: " + turma.getId());
	}
	@Transactional
	public String remover(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			TurmaDao.delete(turmaSelecionada, turmaSelecionada.getId());
			context.addMessage(null, new FacesMessage("Turma Excluida"));
			return "/cadastrarTurma?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			// IMPLEMENTAR ERROS DO POSTGRES
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/cadastrarTurma?faces-redirect=true";
			
		}
		
		
	}

}
