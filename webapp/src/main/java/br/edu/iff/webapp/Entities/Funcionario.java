package br.edu.iff.webapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Funcionario extends Pessoa {

    @ManyToOne()
    @JoinColumn(name = "fk_cargo")
    private Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
