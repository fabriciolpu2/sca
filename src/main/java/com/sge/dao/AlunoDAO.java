package com.sge.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sge.filtro.FiltroAluno;
import com.sge.model.Aluno;

public class AlunoDAO extends GenericDAO<Long, Aluno> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	public AlunoDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> filtrados(FiltroAluno filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(FiltroAluno filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(FiltroAluno filtro) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Aluno.class);
		
		if (StringUtils.isNotEmpty(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria;
	}
	
	// ############### AQUI A JOGADA DE MESTRE KKKKKKKKKKK 
	// #GABIS KKKKKKKK
	public String getUltimaMatricula (String anoLetivo) {
		// Aqui a Pesquisa deve retornar uma String
		String ultimaMatricula = "000000000";
		
		try {
			ultimaMatricula = entityManager.createQuery("SELECT matricula FROM Aluno WHERE anoletivo = '"+anoLetivo+"' ORDER BY id DESC ").setMaxResults(1).getSingleResult().toString();
			System.out.println("################## ultma matricula TRY::::" + ultimaMatricula);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("################## ultma matricula" + ultimaMatricula);
		}
		
		return ultimaMatricula;
	}

}
