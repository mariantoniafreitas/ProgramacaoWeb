package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Disco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String interprete;

    private String genero;

    private String gravadora;

    private double tempoDuracao;

    private int totalMusicas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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