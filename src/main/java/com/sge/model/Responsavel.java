package com.sge.model;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "responsavel")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Responsavel extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	public Responsavel() {
		super();
	}
	
	@Column(length = 20)
	private String grauParentesco;
		
	@Column(length = 30)
	private String profissao;
	
	@Column(length = 30)
	private String localTrabalho;
	

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}
	
	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

}
