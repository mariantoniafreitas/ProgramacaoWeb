package br.edu.iff.webapp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
	@JoinColumn(name="fk_frete")
	private Frete frete;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent(message="Não pode ser no futuro")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar dataHora;
    
    @PositiveOrZero(message="Tem que ser maior ou igual a 0")
    private int qtdProdutos;

    @PositiveOrZero(message="Tem que ser maior ou igual a 0")
    private double totalPedido;

    private boolean concluido;

    @NotBlank(message="Não pode ser em branco ou nulo")
	@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="Deve seguir o padrão do CPF")
    private String cpfCliente;
    
    @ManyToMany
	@JoinTable(name = "compra_produto",
				joinColumns = @JoinColumn(name = "fk_compra"),
				inverseJoinColumns = @JoinColumn(name = "fk_produto"))
	private List<Produto> produto;
    
    public Pedido(String cpfCliente) {
    	this.concluido = false;
    	this.qtdProdutos = 0;
    	this.produto =  new ArrayList<>();
    	this.cpfCliente = cpfCliente;
    }

    public Long getId() {
        return id;
    }

    public int getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(int qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = true;
        this.dataHora = Calendar.getInstance();
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    public void addProduto(Produto produto) {
		this.produto.add(produto);
		this.qtdProdutos++;
		this.totalPedido+=produto.getValor();
	}
	
	public void deleteProduto(Produto produto) {
		this.produto.remove(produto);
		this.qtdProdutos--;
		this.totalPedido-=produto.getValor();
	}
    
    public Pedido() {
    	
    }

}
