package br.edu.iff.webapp.Entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

@Entity
public class Cliente extends Pessoa{

    @OneToMany
	@JoinColumn(name="id_cliente")
	private List<Pedido> pedido;
    
    @Positive(message="Tem que ser maior que 0")
    private double saldoDisponivel;

    public Cliente(String nome, String email, String cpf, String tel, String senha, String endereco, String dataNascimento, double saldoDisponivel) {
    	super(nome, email, cpf, tel, senha, endereco, dataNascimento);
    	this.pedido = new ArrayList<>();
    	this.saldoDisponivel = saldoDisponivel;
    }
    
    public Cliente() {
    	
    }
    
    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }
    
    public void addPedido(Pedido pedido) {
		this.pedido.add(pedido);
	}
	
	public void deletePedido (Pedido pedido) {
		this.pedido.remove(pedido);
	}

}
