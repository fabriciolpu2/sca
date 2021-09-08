package com.sge.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.sge.dao.AlunoDAO;
import com.sge.dao.EnderecoDAO;
import com.sge.filtro.FiltroAluno;
import com.sge.model.Aluno;
import com.sge.model.Endereco;
import com.sge.model.Ocorrencia;
import com.sge.model.Sexo;
import com.sge.model.Siglas;
import com.sge.model.Turno;
import com.sge.util.Transactional;

@Named
@ViewScoped
public class AlunoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoDAO alunoDao;
	private List<Aluno> alunos;

	@Inject 
	private Endereco endereco;
	@Inject
	private EnderecoDAO enderecoDao;
	
	private boolean skip;

	private Aluno aluno = new Aluno();

	private Ocorrencia ocorrencia = new Ocorrencia();

	private FiltroAluno filtro = new FiltroAluno();
	private LazyDataModel<Aluno> model;

	private Float valorMensalidade;
	
	private Aluno alunoSelecionado = new Aluno();


	public AlunoService() {
		model = new LazyDataModel<Aluno>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Aluno> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(alunoDao.quantidadeFiltrados(filtro));

				return alunoDao.filtrados(filtro);
			}

		};
	}

	@Transactional
	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			aluno.setAtivo(true);
			aluno.setDataMatricula(new Date(System.currentTimeMillis()));
			// Chama Funcao de Numero de Matricula
			aluno.setMatricula(matriculaAutomatica());
			
			alunoDao.save(aluno);
			
			context.addMessage(null, new FacesMessage(
					"Aluno salvo com sucesso!"));
			return "/pagMatricula.xhtml?faces-redirect=true id="
					+ aluno.getId();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;
	}

	@Transactional
	public String update() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			aluno.setHoraDesativacao(new SimpleDateFormat("HH:mm")
					.format(new Date()));
			endereco = aluno.getEndereco();
			enderecoDao.update(endereco);
			alunoDao.update(aluno);
			context.addMessage(null,
					new FacesMessage("Atualizado com sucesso!"));
			return "/pesquisarAluno.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	@Transactional
	public void findAll() {
		this.alunos = alunoDao.findAll();
	}	

	@Transactional
	public String getUltimoRegistro() {
		String ultimaMatriculaS = alunoDao.getUltimaMatricula(aluno
				.getAnoLetivo());
		ultimaMatriculaS = ultimaMatriculaS
				.substring(ultimaMatriculaS.length() - 3);
		Integer ultimaMatricula = Integer.parseInt(ultimaMatriculaS);
		ultimaMatricula = ultimaMatricula + 1;

		return ultimaMatricula + "";
	}

	public String matriculaAutomatica() {
		String matricula;
		String ultimo = getUltimoRegistro();

		// ###################################### SEMESTRE REMOVIDO
		// #########################

		// String semestre;
		// @SuppressWarnings("deprecation")
		// Integer mesMatricula = aluno.getDataMatricula().getMonth();

		// Add semestre
		// if (mesMatricula <= 6) {
		// semestre = "01";
		// } else {
		// semestre = "02";
		// }
		//
		// // Add casas decimais
		// if (ultimo.length() == 1) {
		// matricula = aluno.getAnoLetivo() + semestre + "00" + ultimo;
		// } else if (ultimo.length() == 2) {
		// matricula = aluno.getAnoLetivo() + semestre + "0" + ultimo;
		// } else {
		// matricula = aluno.getAnoLetivo() + semestre + ultimo;
		// }

		// Add casas decimais
		if (ultimo.length() == 1) {
			matricula = aluno.getAnoLetivo() + "00" + ultimo;
		} else if (ultimo.length() == 2) {
			matricula = aluno.getAnoLetivo() + "0" + ultimo;
		} else {
			matricula = aluno.getAnoLetivo() + ultimo;
		}

		return matricula;
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Siglas[] getSiglas() {
		return Siglas.values();
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public FiltroAluno getFiltro() {
		return filtro;
	}

	public LazyDataModel<Aluno> getModel() {
		return model;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Float getValorMensalidade() {
		if (aluno.getTurma().getTurno() == Turno.INTEGRAL) {
			valorMensalidade = (aluno.getTurma().getSerie().getValorAnuidade() / 12) * 2;
		} else {
			valorMensalidade = aluno.getTurma().getSerie().getValorAnuidade() / 12;
		}

		return valorMensalidade;
	}

	public void setValorMensalidade(Float valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public void onRowSelect (SelectEvent slc) {
		//FacesMessage msg = new FacesMessage("Aluno Selecionado", ((Aluno) event.getObject()).getId());
       // FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

}
