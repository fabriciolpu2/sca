package com.sge.model;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Funcionario extends Pessoa implements Autenticavel, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	public Funcionario() {
		super();	
	}
	
	@Column(length = 15)
	private String cargo;
	
	@Column(name = "senha")
	private int senha;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public boolean autentica(int senha) {
		if (this.senha != senha) {
			return false;
		}
		// pode fazer outras possíveis verificações, como saber se esse
		// departamento do gerente tem acesso ao Sistema
		return true;
	}
}
