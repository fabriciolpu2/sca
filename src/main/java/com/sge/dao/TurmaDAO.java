package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Turma;

public class TurmaDAO extends GenericDAO<Long, Turma> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	public TurmaDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
}
