package com.sge.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@SuppressWarnings("unchecked")
public class GenericDAO<PK, T> {
    protected EntityManager entityManager;
 
    @Inject
    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
 
    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }
 
    public void save(T entity) {
        entityManager.persist(entity);
    }
 
    public void update(T entity) {
        entityManager.merge(entity);
    }
 
    public void delete(T entity, Long id) {
       // entityManager.remove(entity);
        //em.find(Questao.class, questaoSelecionada.getId()));
        entityManager.remove(entityManager.find(entity.getClass(), id));        
        //QuestaoB.class, questaoSelecionada.getId()));
    }
 
    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
    }
 
    protected Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}