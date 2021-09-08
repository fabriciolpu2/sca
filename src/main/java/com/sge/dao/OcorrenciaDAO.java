package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Ocorrencia;

public class OcorrenciaDAO extends GenericDAO<Long, Ocorrencia> implements Serializable{ 

	private static final long serialVersionUID = 1L;
	
	@Inject
	public OcorrenciaDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
