package br.edu.iff.webapp.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Disco extends Produto {

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
    private double tempoDuracao;

    @Positive(message="Tem que ser maior que 0")
    private int totalMusicas;

    public Disco(double valor, String titulo, String interprete, String genero, String gravadora, double tempoDuracao, int totalMusicas) {
    	super(valor);
    	this.titulo = titulo;
    	this.interprete = interprete;
    	this.genero = genero;
    	this.gravadora = gravadora;
    	this.tempoDuracao = tempoDuracao;
    	this.totalMusicas = totalMusicas;
    }

    public Disco() {
    	
    }
    
    public double getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(double tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
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
        return totalMusicas;
    }

    public void setTotalMusicas(int totalMusicas) {
        this.totalMusicas = totalMusicas;
    }

}