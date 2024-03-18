package br.edu.iff.webapp.Controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Service.FuncionarioService;
import br.edu.iff.webapp.Service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionario")
public class FuncionarioViewController {

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public FuncionarioService funcionarioService;

	@GetMapping("/CRUD")
	public String index() throws Exception {
		return "redirect:/funcionario/CRUD/listarFuncionarios";
	}

	@GetMapping("/CRUD/addForm")
	public String addFuncionarioForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("funcionario_add", new Funcionario());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Funcionario";
	}

	@PostMapping("/CRUD/add")
	public String addFuncionario(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Funcionario funcionario, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = funcionarioService.adicionarFuncionario(login,
                    senha, funcionario.getNome(),funcionario.getEmail(),funcionario.getCpf(), 
                     funcionario.getTel(), funcionario.getEndereco(), funcionario.getDataNascimento(),
                    funcionario.getCargo(), funcionario.getSalario());
			try {
				return "redirect:/funcionario/CRUD/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@GetMapping("/CRUD/listarFuncionarios")
	public String listarFuncionarios(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		if (cpf == null) {
			model.addAttribute("funcionario_lista", funcionarioService.listarFuncionarios());
		} else {
			Funcionario funcionario = funcionarioService.buscarPeloCPF(cpf);
			if (funcionario == null) {
				funcionario = new Funcionario();
			}
			model.addAttribute("funcionario_lista", funcionario);
		}
		return "CRUD_Funcionario";
	}

	@PostMapping("/CRUD/buscaCPF")
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		return "redirect:/funcionario/CRUD/listarFuncionarios?cpf=" + URLEncoder.encode(cpf, "UTF-8");
	}

	@GetMapping("/CRUD/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		Funcionario funcionario = funcionarioService.buscarPeloId(id);
		model.addAttribute("funcionario_edit", funcionario);
		return "CRUD_Funcionario";
	}

	@PostMapping("/CRUD/atualizar")
	public String atualizarFuncionario(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Funcionario funcionario, BindingResult resultado, Model model) {
        String nome = funcionario.getNome();
        String email = funcionario.getEmail();
        String cpf = funcionario.getCpf();
        String tel = funcionario.getTel();
        String endereco = funcionario.getEndereco();
        String dataNascimento = funcionario.getDataNascimento();
        String cargo = funcionario.getCargo();
        double salario = funcionario.getSalario();
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			funcionarioService.atualizarFuncionario(funcionarioService.buscarPeloCPF(cpf).getUsuario().getId(), nome, email, cpf,tel,
					endereco, dataNascimento, cargo, salario);
			if (!login.isEmpty() || !senha.isEmpty()) {
				usuarioService.atualizarUsuario(funcionarioService.buscarPeloCPF(cpf).getUsuario().getId(), login, senha,
						1);
			}
		}
		return "CRUD_Funcionario";
	}

	@GetMapping("/CRUD/deletePorCPF")
	public String deletarFuncionarioCPF(String cpf) throws Exception {
		funcionarioService.deletarFuncionario(funcionarioService.buscarPeloCPF(cpf).getId());
		return "redirect:/funcionario/CRUD/listarFuncionarios";
	}


	@GetMapping("/editarPerfil/{id}")
	public String editarPerfil(@PathVariable("id") Long id, Model model) throws Exception {
		Funcionario funcionario = funcionarioService.buscarPeloId(id);
		model.addAttribute("funcionario_edit", funcionario);
		return "editarPerfil";
	}

	@PostMapping("/editarPerfil/atualizarValoresPerfil")
	public String atualizarValoresPerfil(@RequestParam("login") String login, @RequestParam("senha") String senha, @Valid @ModelAttribute Funcionario funcionario, BindingResult resultado,
			Model model) {
		 	String nome = funcionario.getNome();
	        String email = funcionario.getEmail();
	        String cpf = funcionario.getCpf();
	        String tel = funcionario.getTel();
	        String endereco = funcionario.getEndereco();
	        String dataNascimento = funcionario.getDataNascimento();
	        String cargo = funcionario.getCargo();
	        double salario = funcionario.getSalario();

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			funcionarioService.atualizarFuncionario(funcionarioService.buscarPeloCPF(cpf).getUsuario().getId(), nome, email, cpf,tel,
					endereco, dataNascimento, cargo, salario);
			if (!login.isEmpty() || !senha.isEmpty()) {
				usuarioService.atualizarUsuario(funcionarioService.buscarPeloCPF(cpf).getUsuario().getId(), login, senha,
						1);
			}
			return "redirect:/funcionario/editarPerfil/" + funcionarioService.buscarPeloCPF(cpf).getId();
		}
	}
}
