package br.edu.iff.webapp.Controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Service.ClienteService;
import br.edu.iff.webapp.Service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteViewController {

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public ClienteService clienteService;

	@GetMapping("/CRUD")
	public String index() throws Exception {
		return "redirect:/cliente/CRUD/listarClientes";
	}

	@GetMapping("/CRUD/addForm")
	public String addClienteForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cliente_add", new Cliente());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/add")
	public String addCliente(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = clienteService.adicionarCliente(login, senha, cliente.getNome(), cliente.getEmail(),
					cliente.getCpf(), cliente.getTel(), cliente.getEndereco(), cliente.getDataNascimento());
			try {
				return "redirect:/cliente/CRUD/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@GetMapping("/CRUD/listarClientes")
	public String listarClientes(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		if (cpf == null) {
			model.addAttribute("cliente_lista", clienteService.listarClientes());
		} else {
			Cliente cliente = clienteService.buscarClienteCPF(cpf);
			if (cliente == null) {
				cliente = new Cliente();
			}
			model.addAttribute("cliente_lista", cliente);
		}
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/buscaCPF")
	public String buscarClienteCPF(String cpf) throws Exception {
		return "redirect:/cliente/CRUD/listarClientes?cpf=" + URLEncoder.encode(cpf, "UTF-8");
	}

	@GetMapping("/CRUD/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		Cliente cliente = clienteService.buscarPeloId(id);
		model.addAttribute("cliente_edit", cliente);
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/atualizar")
	public String atualizarCliente(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		String cpf = cliente.getCpf();
		String tel = cliente.getTel();
		String endereco = cliente.getEndereco();
		String dataNascimento = cliente.getDataNascimento();
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			clienteService.atualizarCliente(clienteService.buscarClienteCPF(cpf).getId(), nome, email, cpf, tel,
					endereco, dataNascimento);
			if (!login.isEmpty() || !senha.isEmpty()) {
				usuarioService.atualizarUsuario(clienteService.buscarClienteCPF(cpf).getUsuario().getId(), login, senha,
						0);
			}
		}
		return "CRUD_Cliente";
	}

	@GetMapping("/CRUD/deletePorCPF")
	public String deletarClienteCPF(String cpf) throws Exception {
		clienteService.deletarCliente(clienteService.buscarClienteCPF(cpf).getId());
		return "redirect:/cliente/CRUD/listarClientes";
	}

	@GetMapping("/editarPerfil/{id}")
	public String editarPerfil(@PathVariable("id") Long id, Model model) throws Exception {
		Cliente cliente = clienteService.buscarPeloId(id);
		model.addAttribute("cliente_edit", cliente);
		return "editarPerfil";
	}

	@PostMapping("/editarPerfil/atualizarValoresPerfil")
	public String atualizarValoresPerfil(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		String cpf = cliente.getCpf();
		String tel = cliente.getTel();
		String endereco = cliente.getEndereco();
		String dataNascimento = cliente.getDataNascimento();
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			clienteService.atualizarCliente(clienteService.buscarClienteCPF(cpf).getId(), nome, email, cpf, tel,
					endereco, dataNascimento);
			if (!login.isEmpty() || !senha.isEmpty()) {
				usuarioService.atualizarUsuario(clienteService.buscarClienteCPF(cpf).getUsuario().getId(), login, senha,
						0);
			}
			return "redirect:/cliente/editarPerfil/" + clienteService.buscarClienteCPF(cpf).getId();
		}
	}

	@GetMapping("/cadastro")
	public String cadastroForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cliente_add", new Cliente());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "cadastro";
	}

	@PostMapping("/cadastro/add")
	public String addCadastro(@RequestParam("login") String login, @RequestParam("senha") String senha,
			@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = clienteService.adicionarCliente(login, senha, cliente.getNome(), cliente.getEmail(),
					cliente.getCpf(), cliente.getTel(), cliente.getEndereco(), cliente.getDataNascimento());
			try {
				return "redirect:/cliente/cadastro?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
}
