package com.sge.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.sge.dao.SerieDAO;
import com.sge.model.Serie;
import com.sge.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class SerieService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SerieDAO serieDao;
	private List<Serie> series;
	private Serie serie = new Serie();
	private Serie serieSelecionada = new Serie();
	private Float valorMensalidade;
	
	@Transactional
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {

			if (serie.getId() == null) {
				serieDao.save(serie);
				context.addMessage(null, new FacesMessage(
						"Série salva com sucesso!"));
				return "/cadastrarSerie?faces-redirect=true";
				//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
			} else {
				serieDao.update(serie);
				context.addMessage(null, new FacesMessage("Serie Atualizada"));		
				return "/cadastrarSerie?faces-redirect=true";
			}
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return "/cadastrarSerie?faces-redirect=true";
		}
	}

	@Transactional
	public void findAll() {
		this.series = serieDao.findAll();
	}

	public List<Serie> getSeries() {
		return series;
	}

	public Serie getSerie() {
		return serie;
	}

	// Implementado
	public Serie getSerieSelecionada() {
		return serieSelecionada;
	}

	public void setSerieSelecionada(Serie serieSelecionada) {
		this.serieSelecionada = serieSelecionada;
	}

	public void onRowSelect(SelectEvent slc) {
		serieSelecionada = (Serie) slc.getObject();
		serie = new Serie();
		serie = serieSelecionada;
		System.out.println("Serie selecionada Serie: " + serie.getId());
	}
	@Transactional
	public String remover() {	
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			serieDao.delete(serieSelecionada, serieSelecionada.getId());
			context.addMessage(null, new FacesMessage("Turma Excluida"));
			return "/cadastrarSerie?faces-redirect=true";	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Deu RUIM");
			return "/cadastrarSerie?faces-redirect=true";
		}
		
	}

	public Float getValorMensalidade() {
		valorMensalidade = serie.getValorAnuidade();
		return valorMensalidade;
	}

	public void setValorMensalidade(Float valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}
}
