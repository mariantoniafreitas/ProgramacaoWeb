package br.edu.iff.webapp.Controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Service.FuncionarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionario")
public class FuncionarioViewController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public String listarFuncionarios(Model model) {
		List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
		model.addAttribute("funcionarios", funcionarios);
		return "listarFuncionarios";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicionar() {
		return "adicionarFuncionario";
	}

	@PostMapping("/adicionar")
	public String adicionarFuncionario(@Valid @RequestParam String login, @Valid @RequestParam String senha,
			@Valid @RequestParam String nome, @Valid @RequestParam String email, @Valid @RequestParam String cpf,
			@Valid @RequestParam String tel, @Valid @RequestParam String endereco,
			@Valid @RequestParam String dataNascimento, @Valid @RequestParam String cargo,
			@Valid @RequestParam double salario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "adicionarFuncionario";
		}

		String mensagem = funcionarioService.adicionarFuncionario(login, senha, nome, email, cpf, tel, endereco,
				dataNascimento, cargo, salario);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/funcionario/adicionar";
	}

	@GetMapping("/editar/{id}")
	public String exibirFormularioEditar(@PathVariable Long id, Model model) {
		Funcionario funcionario = funcionarioService.buscarPeloId(id);
		model.addAttribute("funcionario", funcionario);
		return "editarFuncionario";
	}

	@PostMapping("/editar/{id}")
	public String editarFuncionario(@PathVariable Long id, @Valid @RequestParam String nome,
			@Valid @RequestParam String email, @Valid @RequestParam String cpf, @Valid @RequestParam String tel,
			@Valid @RequestParam String endereco, @Valid @RequestParam String dataNascimento,
			@Valid @RequestParam String cargo, @Valid @RequestParam double salario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "editarFuncionario";
		}

		String mensagem = funcionarioService.atualizarFuncionario(id, nome, email, cpf, tel, endereco, dataNascimento,
				cargo, salario);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/funcionario/editar/" + id;
	}

	@GetMapping("/excluir/{id}")
	public String excluirFuncionario(@PathVariable Long id, Model model) {
		String mensagem = funcionarioService.deletarFuncionario(id);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/funcionario/excluir/" + id;
	}
}
