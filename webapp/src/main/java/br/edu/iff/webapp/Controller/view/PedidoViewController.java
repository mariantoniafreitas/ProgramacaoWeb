package br.edu.iff.webapp.Controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

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

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Service.ClienteService;
import br.edu.iff.webapp.Service.DiscoService;
import br.edu.iff.webapp.Service.PedidoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoViewController {
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private DiscoService discoService;

	@Autowired
	public ClienteService clienteService;

	@GetMapping("/CRUD")
	public String index() throws Exception {
		return "redirect:/pedido/CRUD/listarPedidos";
	}

	@GetMapping("/CRUD/addForm")
	public String addPedidoForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("pedido_add", new Pedido());
		model.addAttribute("cliente_lista", clienteService.listarClientes());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Pedido";
	}

	@PostMapping("/CRUD/add")
	public String addPedido(@Valid @ModelAttribute Pedido pedido, BindingResult resultado,
			@RequestParam Long clienteEscolhido, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = pedidoService.adicionarPedido(clienteEscolhido);
			try {
				return "redirect:/pedido/CRUD/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@GetMapping("/CRUD/listarPedidos")
	public String listarPedidos(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		String resposta = request.getParameter("resposta");
		if (cpf == null) {
			model.addAttribute("pedido_lista", pedidoService.listarPedidos());
		} else {
			List<Pedido> pedidos = clienteService.buscarClienteCPF(URLDecoder.decode(cpf, "UTF-8")).getPedido();
			if (pedidos == null) {
				model.addAttribute("pedido_lista", new Pedido());
			} else {
				model.addAttribute("pedido_lista", pedidos);
			}
		}
		if (resposta != null) {
			model.addAttribute("respostaFinalizar", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Pedido";
	}

	@PostMapping("/CRUD/buscaPedido")
	public String buscarPedido(String cpf) throws Exception {
		return "redirect:/pedido/CRUD/listarPedidos?cpf=" + URLEncoder.encode(cpf, "UTF-8");
	}

	@GetMapping("/CRUD/editar")
	public String formEditar(Long id, Model model) throws Exception {
		model.addAttribute("pedido_edit", pedidoService.buscarPeloId(id));
		model.addAttribute("cliente_lista", clienteService.listarClientes());
		model.addAttribute("disco_lista", pedidoService.listarDiscoPeloIdPedido(id));
		return "CRUD_Pedido";
	}

	@PostMapping("/CRUD/atualizar")
	public String atualizarPedido(Long id, @Valid @ModelAttribute Pedido pedido, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			pedidoService.atualizarPedido(id, pedido.getCliente().getId(), pedido.getDataHora(), pedido.getFrete(),
					pedido.getTotalPedido());
			return "redirect:/pedido/CRUD/editar?id=" + id;
		}
	}

	@GetMapping("/CRUD/deletar")
	public String deletarPedido(@RequestParam Long id) throws Exception {
		pedidoService.deletarPedido(id);
		return "redirect:/pedido/CRUD/listarPedidos";
	}

	@PostMapping("/CRUD/addItem")
	public String addDisco(@RequestParam Long id, String titulo) throws Exception {
		pedidoService.adicionarDisco(id, discoService.buscarPeloTitulo(titulo).getId());
		return "redirect:/pedido/CRUD/editar?id=" + id;
	}

	@GetMapping("/CRUD/removeItem")
	public String removeDisco(@RequestParam Long id, Long itemId) throws Exception {
		pedidoService.deletarDisco(id, itemId);
		return "redirect:/pedido/CRUD/editar?id=" + id;
	}

	@GetMapping("/CRUD/finalizar")
	public String finalizarPedido(Long id) throws Exception {
		String resposta = pedidoService.finalizarPedido(id);
		return "redirect:/pedido/CRUD/listarPedidos?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}

	@GetMapping("/carrinho/{id}")
	public String carrinho(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws Exception {
		List<Disco> listaDiscos = pedidoService.listarDiscoPeloIdPedido(id);
		model.addAttribute("pedido", pedidoService.buscarPeloId(id));
		model.addAttribute("disco_lista", listaDiscos);
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaFinalizar", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "carrinho";
	}

	@GetMapping("/carrinho/addItem")
	public String addDiscoCarrinho(Long id, String titulo) throws Exception {
		pedidoService.adicionarDisco(id, discoService.buscarPeloTitulo(titulo).getId());
		return "redirect:/";
	}

	@GetMapping("/carrinho/removeItem")
	public String removeItem(@RequestParam Long id, Long itemId) throws Exception {
		pedidoService.deletarDisco(id, itemId);
		return "redirect:/pedido/carrinho/" + pedidoService.buscarPeloId(id).getCliente().getId();
	}

	@GetMapping("/carrinho/finalizar")
	public String finalizarPedidoCarrinho(Long id) throws Exception {
		String resposta = pedidoService.finalizarPedido(id);
		if (resposta.compareTo("Pedido finalizada com sucesso") == 0) {
			return "redirect:/";
		}
		return "redirect:/pedido/carrinho/" + pedidoService.buscarPeloId(id).getCliente().getId() + "?resposta="
				+ URLEncoder.encode(resposta, "UTF-8");
	}

}
