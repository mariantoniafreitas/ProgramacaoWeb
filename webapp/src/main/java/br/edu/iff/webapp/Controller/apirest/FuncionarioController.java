package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/api/v1/funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService funcionarioService;

	@PostMapping
	@ResponseBody
	@Operation(summary = "Adicionar um funcionário em específico")
	public ResponseEntity<String> adicionarFuncionario(@RequestParam String login, @RequestParam String senha,
			@RequestParam String nome, @RequestParam String email, @RequestParam String cpf, @RequestParam String tel,
			@RequestParam String endereco, @RequestParam String dataNascimento, @RequestParam String cargo,
			@RequestParam double salario) {
		try {
			String mensagem = funcionarioService.adicionarFuncionario(login, senha, nome, email, cpf, tel, endereco,
					dataNascimento, cargo, salario);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar funcionário.");
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um funcionário em específico")
	public ResponseEntity<String> atualizarFuncionario(@PathVariable Long id, @RequestParam String nome,
			@RequestParam String email, @RequestParam String cpf, @RequestParam String tel,
			@RequestParam String endereco, @RequestParam String dataNascimento, @RequestParam String cargo,
			@RequestParam double salario) {
		try {
			String mensagem = funcionarioService.atualizarFuncionario(id, nome, email, cpf, tel, endereco,
					dataNascimento, cargo, salario);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar funcionário.");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um funcionário em específico")
	public ResponseEntity<String> deletarFuncionario(@PathVariable Long id) {
		try {
			String mensagem = funcionarioService.deletarFuncionario(id);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar funcionário.");
		}
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um funcionário em específico")
	public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long id) {
		try {
			Funcionario funcionario = funcionarioService.buscarPeloId(id);
			return ResponseEntity.ok(funcionario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	@ResponseBody
	@Operation(summary = "Listar todos os funcionários")
	public ResponseEntity<List<Funcionario>> listarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
		return ResponseEntity.ok(funcionarios);
	}
}
