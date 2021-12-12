package com.sge.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.event.SelectEvent;

import com.sge.dao.UsuarioDAO;
import com.sge.model.Usuario;
import com.sge.util.Transactional;

@Named
@ViewScoped
public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDao2;
	private List<Usuario> usuarios2;
	private Usuario usuario2 = new Usuario();
	private Usuario usuarioSelecionado = new Usuario();
	private LoginService ls;
	
	private static final int MAX_SIZE = 2 * 1024 * 1024;
	private Part arquivo; 

    public void importa() {
        try {
            String conteudo = new Scanner(arquivo.getInputStream())
                .useDelimiter("\\A").next();
        } catch (IOException e) {
            // trata o erro
        }
    }
    public void valida(FacesContext context, UIComponent component, Object value) {

        Part arquivo = (Part) value;

        if (arquivo.getSize() > MAX_SIZE) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Arquivo muito grande", "O arquivo deve ter o tamanho máximo de 2mb.");
            throw new ValidatorException(msg);
        }

        if (!"text/plain".equals(arquivo.getContentType())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Tipo de arquivo inválido", "O arquivo deve ser do tipo texto.");
            throw new ValidatorException(msg);
        }
    }
    
	
	
	public Part getArquivo() {
		return arquivo;
	}
	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}
	public LoginService getLs() {		
		return ls;
	}

	public void setLs(LoginService ls) {
		this.ls = ls;
	}

	@Transactional
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		//System.out.print(" ############ antes TRY Usuario Sendo Cadastrado" + usuario2.getNome());
		try {
			//System.out.print(" ############ TRY Usuario Sendo Cadastrado" + usuario2.getNome());
			if (usuario2.getId() == null) {			
				//System.out.print(" ############ no IF Usuario Sendo Cadastrado" + usuario2.getNome());	
				usuarioDao2.save(usuario2);
				context.addMessage(null, new FacesMessage(
						"Usuario salvo com sucesso!"));				
				return "/cadastrarFuncionario?faces-redirect=true";
			} else {
				//System.out.print(" ############ no ELSE Usuario Sendo Cadastrado" + usuario2.getNome());				
				usuarioDao2.update(usuario2);
				context.addMessage(null, new FacesMessage("Usuario Atualizada"));		
				
				return "/cadastrarFuncionario?faces-redirect=true";
			}
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.print(" ############ DEU RUIM Usuario Sendo Cadastrado" + usuario2.getNome());
			return null;
		}		
	}
	
	public String atualizar(){
		try{
			usuarioDao2.update(usuario2);			
			return "/cadastrarSerie?faces-redirect=true";			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public void findAll(){
		this.usuarios2 = usuarioDao2.findAll();
	}

	public List<Usuario> getUsuarios() {
		return usuarios2;
	}

	public Usuario getUsuario() {
		return usuario2;
	}

	public void setUsuario(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public void onRowSelect(SelectEvent slc) {
		usuarioSelecionado = (Usuario) slc.getObject();
		usuario2 = new Usuario();
		usuario2 = usuarioSelecionado;
		System.out.println("Usuario selecionado: " + usuario2.getId());
	}
	
	@Transactional
	public String remover() {	
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			usuarioDao2.delete(usuarioSelecionado, usuarioSelecionado.getId());
			context.addMessage(null, new FacesMessage("Usuario Excluida"));
			return "/cadastrarFuncionario?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Deu RUIM");
			return "/cadastrarFuncionario?faces-redirect=true";
		}
		
	}
	
	
}
