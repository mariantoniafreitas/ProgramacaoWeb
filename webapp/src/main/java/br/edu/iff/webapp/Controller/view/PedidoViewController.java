package br.edu.iff.webapp.Controller.view;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Service.PedidoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoViewController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public String listarPedidos(Model model) {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		model.addAttribute("pedidos", pedidos);
		return "listarPedidos";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicionar() {
		return "adicionarPedido";
	}

	@PostMapping("/adicionar")
	public String adicionarPedido(@Valid @RequestParam Long clienteId, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "adicionarPedido";
		}

		String mensagem = pedidoService.adicionarPedido(clienteId);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/adicionar";
	}

	@GetMapping("/editar/{id}")
	public String exibirFormularioEditar(@PathVariable Long id, Model model) {
		Pedido pedido = pedidoService.buscarPeloId(id);
		model.addAttribute("pedido", pedido);
		return "editarPedido";
	}

	@PostMapping("/editar/{id}")
	public String editarPedido(@PathVariable Long id, @Valid @RequestParam Long clienteId,
			@Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data_hora,
			@Valid @RequestParam double frete, @Valid @RequestParam double total_pedido, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "editarPedido";
		}

		String mensagem = pedidoService.atualizarPedido(id, clienteId, data_hora, frete, total_pedido);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/editar/" + id;
	}

	@GetMapping("/excluir/{id}")
	public String excluirPedido(@PathVariable Long id, Model model) {
		String mensagem = pedidoService.deletarPedido(id);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/excluir/" + id;
	}

	@GetMapping("/{id}/discos")
	public String listarDiscosDoPedido(@PathVariable Long id, Model model) {
		List<Disco> discos = pedidoService.listarDiscoPeloIdPedido(id);
		model.addAttribute("discos", discos);
		return "listarDiscosDoPedido";
	}

	@GetMapping("/{id}/adicionarDisco")
	public String exibirFormularioAdicionarDisco(@PathVariable Long id) {
		return "adicionarDiscoAoPedido";
	}

	@PostMapping("/{id}/adicionarDisco")
	public String adicionarDiscoAoPedido(@PathVariable Long id, @Valid @RequestParam Long discoId, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "adicionarItemAoPedido";
		}

		String mensagem = pedidoService.adicionarDisco(id, discoId);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/editar/" + id;
	}

	@GetMapping("/{pedidoId}/deletarDisco/{discoId}")
	public String deletarItemDoPedido(@PathVariable Long pedidoId, @PathVariable Long discoId, Model model) {
		String mensagem = pedidoService.deletarDisco(pedidoId, discoId);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/editar/" + pedidoId;
	}

	@GetMapping("/{id}/finalizar")
	public String finalizarPedido(@PathVariable Long id, Model model) {
		String mensagem = pedidoService.finalizarPedido(id);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/pedido/editar/" + id;
	}

}
