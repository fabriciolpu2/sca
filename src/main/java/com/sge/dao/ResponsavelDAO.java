package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Responsavel;

public class ResponsavelDAO extends GenericDAO<Long, Responsavel> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	public ResponsavelDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
