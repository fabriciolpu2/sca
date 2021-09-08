package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.sge.dao.EnderecoDAO;
import com.sge.model.Endereco;
import com.sge.util.Transactional;


@Named
@javax.faces.view.ViewScoped
public class EnderecoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDAO enderecoDao;
	private List<Endereco> enderecos;
	private Endereco endereco = new Endereco();

	@Transactional
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			enderecoDao.save(endereco);
			context.addMessage(null, new FacesMessage(
					"Série salva com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	@Transactional
	public void findAll() {
		this.enderecos = enderecoDao.findAll();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}
}
