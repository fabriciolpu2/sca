package com.sge.model;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.PERSIST, optional = false)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
    @Inject
    public Pessoa() {
    	this.endereco = new Endereco();
    }
    
	@Id	
//@GeneratedValue(strategy = GenerationType.TABLE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(length = 30)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Siglas uf;
	
	@Column(length = 14)
	private String cpf;
	
	@Column(length = 10)	
	private String rg;
	
	@Column(length = 15)
	private String rgEmissor;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Siglas rgUf;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataNasc")
	private Date dataNasc;
	
	@Column(length = 30)
	private String naturalidade;
	
	@Column(length = 30)
	private String nacionalidade;
	
	@Column(length = 15)
	private String telefoneRes;
	
	@Column(length = 15)
	private String celular;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}
	
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefoneRes() {
		return telefoneRes;
	}
	
	public void setTelefoneRes(String telefoneRes) {
		this.telefoneRes = telefoneRes;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Siglas getUf() {
		return uf;
	}

	public void setUf(Siglas uf) {
		this.uf = uf;
	}

	public Siglas getRgUf() {
		return rgUf;
	}

	public void setRgUf(Siglas rgUf) {
		this.rgUf = rgUf;
	}

	
	public String getRgEmissor() {
		return rgEmissor;
	}

	public void setRgEmissor(String rgEmissor) {
		this.rgEmissor = rgEmissor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
