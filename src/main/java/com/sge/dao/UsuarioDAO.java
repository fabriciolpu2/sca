package com.sge.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.sge.model.Usuario;

public class UsuarioDAO extends GenericDAO<Long, Usuario>  implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	public UsuarioDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Usuario getUsuario(String nomeUsuario, String senha) { 
		try { 
			Usuario usuario = (Usuario) entityManager.createQuery( "SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha") 
					 .setParameter("name", nomeUsuario) .setParameter("senha", senha).getSingleResult(); return usuario; } 
		catch (NoResultException e) {
			return null; 
			} 
		} 
	public boolean inserirUsuario(Usuario usuario) { 
		try { 
			entityManager.persist(usuario); 
			return true; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return false; 
				} 
		} 
	public boolean deletarUsuario(Usuario usuario) { 
		try { 
			entityManager.remove(usuario); 
			return true; 
			} catch (Exception e) { 
				e.printStackTrace(); return false; 
				} 
		} 
	}
