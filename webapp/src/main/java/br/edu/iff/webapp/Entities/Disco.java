package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Disco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, length = 60)
	private String titulo = " ";

	@NotNull(message = "O interprete não pode ser nulo.")
	@Size(min = 1, max = 60, message = "Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
	private String interprete;

	@NotNull(message = "O valor não pode ser nulo.")
	@Positive(message = "Tem que ser maior que 0")
	private double valor;

	@NotBlank(message = "O gênero não pode ser nulo.")
	@Size(min = 1, max = 20, message = "Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String genero;

	@Size(min = 1, max = 20, message = "Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String gravadora;

	@NotNull(message = "O tempo de duração não pode ser nulo.")
	@Positive(message = "Tem que ser maior que 0")
	private double tempo_duracao;

	@Positive(message = "Tem que ser maior que 0")
	@NotNull(message = "O total de músicas não pode ser nulo.")
	private int total_musicas;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@JsonBackReference
	private Pedido pedido;

	public Disco(double valor, String titulo, String interprete, String genero, String gravadora, double tempo_duracao,
			int total_musicas) {
		this.valor = valor;
		this.titulo = titulo;
		this.interprete = interprete;
		this.genero = genero;
		this.gravadora = gravadora;
		this.tempo_duracao = tempo_duracao;
		this.total_musicas = total_musicas;
	}

	public Disco() {
	}

	public Long getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getTempoDuracao() {
		return tempo_duracao;
	}

	public void setTempoDuracao(double tempo_duracao) {
		this.tempo_duracao = tempo_duracao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getGravadora() {
		return gravadora;
	}

	public void setGravadora(String gravadora) {
		this.gravadora = gravadora;
	}

	public int getTotalMusicas() {
		return total_musicas;
	}

	public void setTotalMusicas(int total_musicas) {
		this.total_musicas = total_musicas;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}