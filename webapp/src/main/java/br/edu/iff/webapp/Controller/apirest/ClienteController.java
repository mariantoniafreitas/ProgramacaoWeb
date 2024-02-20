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

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/api/v1/cliente")
public class ClienteController {

	@Autowired
	public ClienteService clienteService;

	@PostMapping
	@ResponseBody
	@Operation(summary = "Adicionar um cliente em específico")
	public ResponseEntity<String> adicionarCliente(@RequestParam String login, @RequestParam String senha,
			@RequestParam String nome, @RequestParam String email, @RequestParam String cpf, @RequestParam String tel,
			@RequestParam String endereco, @RequestParam String dataNascimento) {
		try {
			String mensagem = clienteService.adicionarCliente(login, senha, nome, email, cpf, tel, endereco,
					dataNascimento);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar cliente.");
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um cliente em específico")
	public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestParam String nome,
			@RequestParam String email, @RequestParam String cpf, @RequestParam String tel,
			@RequestParam String endereco, @RequestParam String dataNascimento) {
		try {
			String mensagem = clienteService.atualizarCliente(id, nome, email, cpf, tel, endereco, dataNascimento);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar cliente.");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um cliente em específico")
	public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
		try {
			String mensagem = clienteService.deletarCliente(id);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar cliente.");
		}
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um cliente em específico")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
		try {
			Cliente cliente = clienteService.buscarPeloId(id);
			return ResponseEntity.ok(cliente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	@ResponseBody
	@Operation(summary = "Listar todos os clientes")
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> clientes = clienteService.listarClientes();
		return ResponseEntity.ok(clientes);
	}

}
