package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class Funcionario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cargo;
	private double salario;

	public Funcionario(String nome, String email, String cpf, String tel, String endereco, String dataNascimento,
			String cargo, double salario) {
		super(nome, email, cpf, tel, endereco, dataNascimento);
		this.cargo = cargo;
		this.salario = salario;
	}

	public Funcionario() {

	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}