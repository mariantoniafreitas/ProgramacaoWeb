package br.edu.iff.webapp.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
	@JoinColumn(name="fk_cargo")
    private Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public Long getId() {
        return id;
    }
    
    public Funcionario() {}
    
    public Funcionario(String nome, String email, String cpf, String tel, String endereco, String dataNascimento) {
    	super(nome, email, cpf, tel, endereco, dataNascimento);
    	
    }

}
