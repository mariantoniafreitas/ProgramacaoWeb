package br.edu.iff.webapp.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Disco extends Produto {
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//	
//	@Positive(message="Tem que ser maior que 0")
//    private double valor;
//	
	@NotBlank(message="Não pode ser em branco ou nulo")
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(unique=true, length = 60)
    private String titulo = " ";

    @Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
    private String interprete;

    @Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
    private String genero;

    @Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
    private String gravadora;

    @Positive(message="Tem que ser maior que 0")
    private double tempo_duracao;

    @Positive(message="Tem que ser maior que 0")
    private int total_musicas;

    public Disco(double valor, String titulo, String interprete, String genero, String gravadora, double tempo_duracao, int total_musicas) {
    	super(valor);
    	//this.valor = valor;
    	this.titulo = titulo;
    	this.interprete = interprete;
    	this.genero = genero;
    	this.gravadora = gravadora;
    	this.tempo_duracao = tempo_duracao;
    	this.total_musicas = total_musicas;
    }

    public Disco() {}
    
//    public Long getId() {
//        return id;
//    }
//
//    public double getValor() {
//        return valor;
//    }
//
//    public void setValor(double valor) {
//        this.valor = valor;
//    }
    
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

}