package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.sge.dao.FuncionarioDAO;
import com.sge.model.Funcionario;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class FuncionarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDao;
	private List<Funcionario> funcionarios;
	
	@Inject
	private Funcionario funcionario;

	@Transactional
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			funcionarioDao.save(funcionario);
			context.addMessage(null, new FacesMessage(
					"Funcionario salvo com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	@Transactional
	public void findAll() {
		this.funcionarios = funcionarioDao.findAll();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
}
