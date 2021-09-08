package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.sge.model.Carne;

public class CarneDAO extends GenericDAO<Long, Carne> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	public CarneDAO(EntityManager entityManager) {
		super(entityManager);	
	}	
}
