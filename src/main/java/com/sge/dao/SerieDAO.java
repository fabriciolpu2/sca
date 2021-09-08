package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Serie;

public class SerieDAO extends GenericDAO<Long, Serie> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	public SerieDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
