package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Endereco;

public class EnderecoDAO extends GenericDAO<Long, Endereco> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	public EnderecoDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
