package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.sge.dao.EnderecoDAO;
import com.sge.dao.ProfessorDAO;
import com.sge.model.Endereco;
import com.sge.model.Professor;
import com.sge.model.Sexo;
import com.sge.model.Siglas;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class ProfessorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfessorDAO professorDao;
	@Inject
	private EnderecoDAO enderecoDAO;
	private List<Professor> professores;
	

	@Inject
	private Professor professor;
	@Inject
	private Endereco endereco;
	@Inject
	private Professor professorSelecionado;

	@Transactional
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			if (professor.getId() == null) {
				professorDao.save(professor);
				context.addMessage(null, new FacesMessage(
						"Professor salvo com sucesso!"));
				return "/cadastrarProfessor?faces-redirect=true";
			} else {
				endereco = professor.getEndereco();
				enderecoDAO.update(endereco);
				professorDao.update(professor);
				context.addMessage(null, new FacesMessage(
						"Professor Atualizado!"));
				return "/cadastrarProfessor?faces-redirect=true";
			}

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/cadastrarProfessor?faces-redirect=true";
		}
	}

	@Transactional
	public void findAll() {
		this.professores = professorDao.findAll();
	}

	public List<Professor> getProfessores() {
		professores = professorDao.findAll();
		return professores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public void onRowSelect(SelectEvent slc) {
		professorSelecionado = (Professor) slc.getObject();
		professor = new Professor();
		professor.setEndereco(new Endereco());
		professor = professorSelecionado;
		System.out.println("Professor selecionado: " + professor.getId());
	}

	@Transactional
	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			professorDao.delete(professorSelecionado, professorSelecionado.getId());
			context.addMessage(null, new FacesMessage("Professor Excluido"));
			return "/cadastrarProfessor?faces-redirect=true";
		} catch (Exception e) {
			// IMPLEMENTAR ERROS DO POSTGRES
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			System.out.println("Deu RUim");
			return "/cadastrarProfessor?faces-redirect=true";
			
		}

	}

	public Siglas[] getSiglas() {
		return Siglas.values();
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

}
