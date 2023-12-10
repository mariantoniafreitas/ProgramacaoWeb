package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "col_nome")
    private String nome;

    private double salario;

    @Column(unique = true, length = 30)
    private String descricao;

    private int nivelAcesso;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

}
