package br.edu.iff.webapp.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Funcionario extends Pessoa {

    
    @ManyToOne()
	@JoinColumn(name="fk_cargo")
    private Cargo cargo;
    
    public Funcionario() {}
    
    public Funcionario(String nome, String email, String cpf, String tel,String senha, String endereco, String dataNascimento) {
    	super(nome, email, cpf, tel, senha, endereco, dataNascimento);
    	
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    

}
