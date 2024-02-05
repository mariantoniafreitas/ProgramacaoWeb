package br.edu.iff.webapp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, length = 14)
    private String login;
    
    @Column()
    private String senha;
    
    @ManyToMany(fetch = FetchType.EAGER)
	private List<Permissao> permissoes;

    public Long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public List<Permissao> getPermissoes() {
		return permissoes;
	}
	
	public void addPermissao(Permissao permissao) {
		this.permissoes.add(permissao);
	}
    
    public Usuario() {
    	
    }
    
    public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.permissoes = new ArrayList<>();
	}
}