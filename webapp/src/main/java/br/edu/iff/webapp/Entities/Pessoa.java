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

    @Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
    private String nome;

    @Email(message="Tem que ser em formato de email")
	@Column(length = 60)
    private String email;

    @NotBlank(message="Não pode ser em branco ou nulo")
	@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="Deve seguir o padrão do CPF")
	@Column(unique=true, length = 14)
    private String cpf = " ";

    @NotBlank(message="Não pode ser em branco ou nulo")
   	//@Pattern(regexp="\\([0-9]{2}\\) [0-9]{5}-[0-9]{4}", message="Deve seguir o padrão do CPF")
   	@Column(unique=true, length = 14)
    private String tel;

    @Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	@Column(length = 60)
    private String endereco;
    
    @Size(min = 1, max = 20, message = "Tem que ter entre 1 e 20 caracteres")
    @Column(length = 20)
    //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de data de nascimento inválido. Use o formato yyyy-MM-dd")
    private String dataNascimento;

    
    @Size(min=1,max=20,message="Tem que ter entre 1 e 20 caractéres")
	@Column(length = 20)
	private String senha;

    @ManyToOne()
	@JoinColumn(name="fk_pessoa")
	private Usuario usuario;

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
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Pessoa () {}
    
    public Pessoa(String nome, String email, String cpf, String tel, String senha, String endereco, String dataNascimento) {
    	this.nome = nome;
    	this.email = email;
    	this.cpf = cpf; 
    	this.tel = tel;
    	this.senha = senha;
    	this.endereco = endereco;
    	this.dataNascimento = dataNascimento; 
    }
}