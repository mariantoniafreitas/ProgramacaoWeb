package br.edu.iff.webapp.Controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Service.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteViewController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public String listarClientes(Model model) {
		List<Cliente> clientes = clienteService.listarClientes();
		model.addAttribute("clientes", clientes);
		return "listarClientes";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicionar() {
		return "adicionarCliente";
	}

	@PostMapping("/adicionar")
	public String adicionarCliente(@Valid @RequestParam String login, @Valid @RequestParam String senha,
			@Valid @RequestParam String nome, @Valid @RequestParam String email, @Valid @RequestParam String cpf,
			@Valid @RequestParam String tel, @Valid @RequestParam String endereco, @RequestParam String dataNascimento,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "adicionarCliente";
		}

		String mensagem = clienteService.adicionarCliente(login, senha, nome, email, cpf, tel, endereco,
				dataNascimento);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/cliente/adicionar";
	}

	@GetMapping("/editar/{id}")
	public String exibirFormularioEditar(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.buscarPeloId(id);
		model.addAttribute("cliente", cliente);
		return "editarCliente";
	}

	@PostMapping("/editar/{id}")
	public String editarCliente(@PathVariable Long id, @Valid @RequestParam String nome,
			@Valid @RequestParam String email, @Valid @RequestParam String cpf, @Valid @RequestParam String tel,
			@Valid @RequestParam String endereco, @RequestParam String dataNascimento, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "editarCliente";
		}

		String mensagem = clienteService.atualizarCliente(id, nome, email, cpf, tel, endereco, dataNascimento);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/cliente/editar/" + id;
	}

	@GetMapping("/excluir/{id}")
	public String excluirCliente(@PathVariable Long id, Model model) {
		String mensagem = clienteService.deletarCliente(id);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/cliente/excluir/" + id;
	}
}
