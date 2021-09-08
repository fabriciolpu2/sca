package com.sge.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carne")
public class Carne implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "aluno")
	private Aluno aluno;
	
	private Float valorMensalidade;
	private Date dataPagamento;
	private Date vencimentoParcela;
	private Float valorDesconto;
	private Float juros;
	private Float valorFinal;
	private Integer numParcela;
	private Boolean pago;

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
		//this.valorMensalidade = aluno.getTurma().getSerie().getValorAnuidade()/12;
		this.valorMensalidade = valorMensalidade;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getVencimentoParcela() {
		return vencimentoParcela;
	}

	public void setVencimentoParcela(Date vencimentoParcela) {
		this.vencimentoParcela = vencimentoParcela;
	}

	public Float getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Float valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Float getJuros() {
		return juros;
	}

	public void setJuros(Float juros) {
		this.juros = juros;
	}

	public Float getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Float valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
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
		Carne other = (Carne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}
}
