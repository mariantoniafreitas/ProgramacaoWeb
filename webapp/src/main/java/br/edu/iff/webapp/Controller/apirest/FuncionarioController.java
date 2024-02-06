package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService funcionarioService;

	@PostMapping("")
	@ResponseBody
	@Operation(description = "Adicionar um novo funcionario")
	public String addFuncionario(String nome, String email, String cpf, String tel, String senha, String endereco,
			String dataNascimento, String descricao) throws Exception {
		return funcionarioService
				.addFuncionario(new Funcionario(nome, email, cpf, tel, senha, endereco, dataNascimento), descricao);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(description = "Atualizar um funcionario")
	public String atualizarFuncionario(@PathVariable("id") Long id, String nome, String email, String senha,
			String descricao) {
		Funcionario fBusca = funcionarioService.getFuncionarioById(id);
		if (fBusca == null) {
			return "Funcionario não achado";
		} else {
			return funcionarioService.atualizarFuncionario(fBusca.getCpf(), nome, email, senha, descricao);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(description = "Deletar um funcionario")
	public String deletarFuncionarioCPF(@PathVariable("id") Long id) {
		Funcionario fBusca = funcionarioService.getFuncionarioById(id);
		if (fBusca == null) {
			return "Funcionario não achado";
		} else {
			return funcionarioService.deletarFuncionarioCPF(fBusca.getCpf());
		}
	}

	@GetMapping("")
	@ResponseBody
	@Operation(description = "Listar todos os funcionarios")
	public List<Funcionario> listarFuncionarios() {
		return funcionarioService.listarFuncionarios();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(description = "Informacoes de um funcionario")
	public Funcionario buscarFuncionarioId(@PathVariable("id") Long id) {
		return funcionarioService.getFuncionarioById(id);
	}
}
