package br.edu.iff.webapp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany
	@JoinColumn(name = "cliente_id")
	private List<Pedido> pedido;

	private double saldoDisponivel;

	public Cliente(String nome, String email, String cpf, String tel, String endereco, String dataNascimento) {
		super(nome, email, cpf, tel, endereco, dataNascimento);
		this.pedido = new ArrayList<>();
		this.saldoDisponivel = 0;
	}

	public Cliente() {

	}

	public double getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(double saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

	public void adicionarPedido(Pedido pedido) {
		this.pedido.add(pedido);
	}

	public void deletarPedido(Pedido pedido) {
		this.pedido.remove(pedido);
	}
	
	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

}