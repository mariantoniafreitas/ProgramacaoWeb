package br.edu.iff.webapp.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "A data do pedido não pode ser nula")
	@PastOrPresent(message = "Não pode ser no futuro")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime data_hora;

	@PositiveOrZero(message = "Tem que ser maior ou igual a 0")
	private double frete;

	@PositiveOrZero(message = "Tem que ser maior ou igual a 0")
	private double total_pedido;

	private boolean concluido;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Disco> discos;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;

	public Pedido(Cliente cliente) {
		this.concluido = false;
		this.cliente = cliente;
		this.discos = new ArrayList<>();
	}

	public Pedido() {

	}

	public Long getId() {
		return id;
	}

	public double getTotalPedido() {
		return total_pedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.total_pedido = totalPedido;
	}

	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}

	public List<Disco> getDiscos() {
		return discos;
	}

	public void setDiscos(List<Disco> discos) {
		this.discos = discos;
	}

	public void adicionarDisco(Disco disco) {
		this.discos.add(disco);
		this.total_pedido += disco.getValor();
		disco.setPedido(this);
	}

	public void deletarDisco(Disco disco) {
		this.discos.remove(disco);
		this.total_pedido -= disco.getValor();
		disco.setPedido(null);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void concluirPedido() {
		this.concluido = true;
		this.data_hora = LocalDateTime.now();
	}

	public boolean isConcluido() {
		return this.concluido;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public void setData_hora(LocalDateTime data_hora) {
		this.data_hora = data_hora;
	}

}