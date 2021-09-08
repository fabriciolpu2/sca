package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Long, Funcionario> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	public FuncionarioDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
