package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Cargo implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Positive(message="Tem que ser maior que 0")
    private double salario;
    
    @NotBlank(message="Não pode ser em branco ou nulo")
	@Size(min=1,max=30,message="Tem que ter entre 1 e 30 caractéres")
	@Column(unique=true, length = 30)
    private String descricao;
    
    @PositiveOrZero(message="Tem que ser maior ou igual a 0")
    private int nivelAcesso;

    public Long getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public Cargo(String descricao, double salario, int nivelAcesso) {
		super();
		this.descricao = descricao;
		this.salario = salario;
		this.nivelAcesso = nivelAcesso;
	}
    
    public Cargo() {
    	 
     }
}