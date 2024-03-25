package br.edu.iff.webapp.Entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String acesso;

    @ManyToMany(mappedBy = "permissoes")
	private List<Usuario> usuarios;
    
    
    public Long getId() {
        return id;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }
    
    public Permissao(String acesso) {
    	this.acesso = acesso;
    }
    
    public Permissao() {
    	
    }

}
