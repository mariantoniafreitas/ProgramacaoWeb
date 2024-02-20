package br.edu.iff.webapp.Entities;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome não pode ser em branco ou nulo")
    @Size(min = 3, max = 60, message = "O nome deve conter entre 3 e 60 caracteres")
    @Column(length = 60)
	private String nome;

	@Email(message = "O email deve ser válido")
	@NotBlank(message = "O email não pode ser em branco ou nulo")
	@Column(length = 60)
	private String email;

	@NotBlank(message = "O CPF não pode ser em branco ou nulo")
	@Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
	@Column(unique = true, length = 11)
	private String cpf;

	@NotBlank(message = "O telefone não pode ser em branco ou nulo") 
	@Pattern(regexp = "\\d{9,11}", message = "O telefone deve conter entre 9 e 11 dígitos numéricos")
	@Column(unique = true, length = 14)
	private String tel;

	@Size(min = 20, max = 100, message = "O endereço deve conter entre 20 e 100 caracteres")
    @NotBlank(message = "O endereço não pode ser em branco ou nulo")
    @Column(length = 100)
	private String endereco;

	@Size(min = 1, max = 20, message = "Tem que ter entre 1 e 20 caracteres")
	@Column(length = 20)
	private String dataNascimento;

	@ManyToOne()
	@JoinColumn(name = "fk_pessoa")
	private Usuario usuario;

	public Pessoa(String nome, String email, String cpf, String tel, String endereco, String dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.tel = tel;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTel() {
		return tel;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}