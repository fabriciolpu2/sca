package com.sge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Aluno extends Pessoa implements Autenticavel, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	public Aluno() {	
		super();		
		this.mae = new Responsavel();	
		this.pai = new Responsavel();
		this.responsavel = new Responsavel();
		this.carnes = new ArrayList<Carne>();
	}

	
	@Column(length = 15)
	private String telefone1;
	private String falarCom1;	
	
	@Column(length = 15)	
	private String telefone2;
	private String falarCom2;	
	
	@Column(length = 15)	
	private String telefone3;
	private String falarCom3;	
	
	@Column(length = 15)
	private String telefone4;
	private String falarCom4;
	
	@Column(length = 15)
	private String telefone5;
	private String falarCom5;
	@Column(length = 15)
	private String telefone6;
	private String falarCom6;
	
	
	
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getFalarCom1() {
		return falarCom1;
	}

	public void setFalarCom1(String falarCom1) {
		this.falarCom1 = falarCom1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getFalarCom2() {
		return falarCom2;
	}

	public void setFalarCom2(String falarCom2) {
		this.falarCom2 = falarCom2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getFalarCom3() {
		return falarCom3;
	}

	public void setFalarCom3(String falarCom3) {
		this.falarCom3 = falarCom3;
	}

	public String getTelefone4() {
		return telefone4;
	}

	public void setTelefone4(String telefone4) {
		this.telefone4 = telefone4;
	}

	public String getFalarCom4() {
		return falarCom4;
	}

	public void setFalarCom4(String falarCom4) {
		this.falarCom4 = falarCom4;
	}

	public String getTelefone5() {
		return telefone5;
	}

	public void setTelefone5(String telefone5) {
		this.telefone5 = telefone5;
	}

	public String getFalarCom5() {
		return falarCom5;
	}

	public void setFalarCom5(String falarCom5) {
		this.falarCom5 = falarCom5;
	}

	public String getTelefone6() {
		return telefone6;
	}

	public void setTelefone6(String telefone6) {
		this.telefone6 = telefone6;
	}

	public String getFalarCom6() {
		return falarCom6;
	}

	public void setFalarCom6(String falarCom6) {
		this.falarCom6 = falarCom6;
	}


	@ManyToOne(optional = true)
	@JoinColumn(name = "turma")
	private Turma turma;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataMatricula")
	private Date dataMatricula;
	
	@Column(name = "anoLetivo", length = 4)
	private String anoLetivo;
	
	@Column(name = "docPendente")
	private boolean docPendente;
	
	@Column(name = "descricaoDocPendente")
	private String descricaoDocPendente;
	
	@Column(name = "matriculaResponsavel")
	private String matriculaResponsavel;
	
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "funcionarioMatricula")
	private Funcionario funcionarioMatricula;
	
	@Column(length = 15)
	private String codMec;
	
	@Column(length = 30)
	private String matricula;
	
	@Column
	private boolean conveniado;

	@Column(length = 100)
	private String descricaoConvenio;

	@Column
	private boolean eAluno;

	@Column(length = 30)
	private String escolaOrigem;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "pai")
	private Responsavel pai;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "mae")
	private Responsavel mae;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "responsavel")
	private Responsavel responsavel;

	@Column(length = 30)
	private String moraCom;
	
	@Column(length = 30, name = "rendaFamiliar")
	private String rendaFamiliar;

	@Column(length = 30, name = "tempoResidencia" )
	private String tempoResidencia;

	@Column(name = "numIrmaos")
	private int numIrmaos;

	@Column(name = "pne")
	private boolean pne;

	@Column(length = 100)
	private String descricaoPne;

	@Column(name = "doente")
	private boolean doente;

	@Column(length = 30)
	private String doencaCuidados;

	@Column(name = "alergias")
	private boolean alergias;

	@Column(length = 100)
	private String descricaoAlergias;

	@Column(name = "ativo")
	private boolean ativo;
	
	@Column(name = "dataDesativacao")
	private Date dataDesativacao;
	
	@Column(name = "horaDesativacao")
	private String horaDesativacao;
	
	@Column(name = "responsavelDesativacao")
	private String responsavelDesativacao;
	
	@OneToMany(mappedBy="aluno")
	private List<Ocorrencia> ocorrencias;
	
	@OneToMany(mappedBy="aluno" , fetch = FetchType.LAZY)
	private List<Carne> carnes;
	
	public List<Carne> getCarnes() {
		return carnes;
	}

	public void setCarnes(List<Carne> carnes) {
		this.carnes = carnes;
	}


	public String getCodMec() {
		return codMec;
	}
	

	public void setCodMec(String codMec) {
		this.codMec = codMec;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isConveniado() {
		return conveniado;
	}

	public void setConveniado(boolean conveniado) {
		this.conveniado = conveniado;
	}

	public String getDescricaoConvenio() {
		return descricaoConvenio;
	}

	public void setDescricaoConvenio(String descricaoConvenio) {
		this.descricaoConvenio = descricaoConvenio;
	}

	public boolean iseAluno() {
		return eAluno;
	}

	public void seteAluno(boolean eAluno) {
		this.eAluno = eAluno;
	}

	public String getEscolaOrigem() {
		return escolaOrigem;
	}

	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}

	public Responsavel getPai() {
		return pai;
	}

	public void setPai(Responsavel pai) {
		this.pai = pai;
	}

	public Responsavel getMae() {
		return mae;
	}

	public void setMae(Responsavel mae) {
		this.mae = mae;
	}

	public String getMoraCom() {
		return moraCom;
	}

	public void setMoraCom(String moraCom) {
		this.moraCom = moraCom;
	}

	public String getTempoResidencia() {
		return tempoResidencia;
	}

	public void setTempoResidencia(String tempoResidencia) {
		this.tempoResidencia = tempoResidencia;
	}

	public int getNumIrmaos() {
		return numIrmaos;
	}

	public void setNumIrmaos(int numIrmaos) {
		this.numIrmaos = numIrmaos;
	}

	public boolean isPne() {
		return pne;
	}

	public void setPne(boolean pne) {
		this.pne = pne;
	}

	public String getDescricaoPne() {
		return descricaoPne;
	}

	public void setDescricaoPne(String descricaoPne) {
		this.descricaoPne = descricaoPne;
	}

	public boolean isDoente() {
		return doente;
	}

	public void setDoente(boolean doente) {
		this.doente = doente;
	}

	public String getDoencaCuidados() {
		return doencaCuidados;
	}

	public void setDoencaCuidados(String doencaCuidados) {
		this.doencaCuidados = doencaCuidados;
	}

	public boolean isAlergias() {
		return alergias;
	}

	public void setAlergias(boolean alergias) {
		this.alergias = alergias;
	}

	public String getDescricaoAlergias() {
		return descricaoAlergias;
	}

	public void setDescricaoAlergias(String descricaoAlergias) {
		this.descricaoAlergias = descricaoAlergias;
	}

	@Override
	public boolean autentica(int senha) {
		// TODO Auto-generated method stub
		return false;
	}


	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(String rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public boolean isDocPendente() {
		return docPendente;
	}

	public void setDocPendente(boolean docPendente) {
		this.docPendente = docPendente;
	}

	public String getDescricaoDocPendente() {
		return descricaoDocPendente;
	}

	public void setDescricaoDocPendente(String descricaoDocPendente) {
		this.descricaoDocPendente = descricaoDocPendente;
	}

	public String getMatriculaResponsavel() {
		return matriculaResponsavel;
	}

	public void setMatriculaResponsavel(String matriculaResponsavel) {
		this.matriculaResponsavel = matriculaResponsavel;
	}

	public Funcionario getFuncionarioMatricula() {
		return funcionarioMatricula;
	}

	public void setFuncionarioMatricula(Funcionario funcionarioMatricula) {
		this.funcionarioMatricula = funcionarioMatricula;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public Date getDataDesativacao() {
		return dataDesativacao;
	}


	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}


	public String getResponsavelDesativacao() {
		return responsavelDesativacao;
	}


	public void setResponsavelDesativacao(String responsavelDesativacao) {
		this.responsavelDesativacao = responsavelDesativacao;
	}


	public String getHoraDesativacao() {
		return horaDesativacao;
	}


	public void setHoraDesativacao(String horaDesativacao) {
		this.horaDesativacao = horaDesativacao;
	}
	
	

}
