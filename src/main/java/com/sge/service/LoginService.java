package com.sge.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.sge.dao.UsuarioDAO;
import com.sge.model.Usuario;

@Named
@RequestScoped
public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private Usuario usuario;

	
	public String envia() {
		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
//		System.out.print(usuario.getNome());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Usuário não encontrado!", "Erro no Login!"));
			return null;
		} else {
			return "/index?faces-redirect=true";
		}
	}

	public String getNomeUser() {
		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		String nome = usuario.getNome();
//		System.out.print(nome);
//		System.out.print(usuario.getNome());
		return nome;
	}
	
	public Integer getPerfilUsuario() {
		//usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		Integer perfil = usuario.getPerfil();
//		System.out.print(nome);
//		System.out.print(usuario.getNome());
		return perfil;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String Logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
}
