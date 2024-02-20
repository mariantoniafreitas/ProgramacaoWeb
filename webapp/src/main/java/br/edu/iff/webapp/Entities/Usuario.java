package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 14)
	private String login;

	private int permissao;

	@Size(min = 6, max = 20, message = "Tem que ter entre 6 e 20 caractéres")
	@Column(length = 20)
	private String senha;

	public Usuario(String login, String senha, int permissao) {
		this.login = login;
		this.senha = senha;
		this.permissao = permissao;
	}

	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
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

}