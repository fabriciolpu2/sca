package com.sge.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sge.model.Professor;

public class ProfessorDAO extends GenericDAO<Long, Professor> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	public ProfessorDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<Professor> findAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Professor> criteriaQuery = builder.createQuery(Professor.class);
		
		Root<Professor> professor = criteriaQuery.from(Professor.class);
		professor.fetch("endereco");
		criteriaQuery.select(professor);
		
		TypedQuery<Professor> query = this.entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
