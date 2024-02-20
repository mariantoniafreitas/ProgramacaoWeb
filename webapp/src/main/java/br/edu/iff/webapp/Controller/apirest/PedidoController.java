package br.edu.iff.webapp.Controller.apirest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/api/v1/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	@ResponseBody
	@Operation(summary = "Adicionar um pedido em específico")
	public ResponseEntity<String> adicionarPedido(@RequestParam Long clienteId) {
		try {
			String mensagem = pedidoService.adicionarPedido(clienteId);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar pedido.");
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um pedido em específico")
	public ResponseEntity<String> atualizarPedido(@PathVariable("id") Long id, @RequestParam Long clienteId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data_hora,
			@RequestParam double frete, @RequestParam double total_pedido) {

		try {
			String mensagem = pedidoService.atualizarPedido(id, clienteId, data_hora, frete, total_pedido);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar pedido.");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um pedido em específico")
	public ResponseEntity<String> deletarPedido(@PathVariable("id") Long id) {
		try {
			String mensagem = pedidoService.deletarPedido(id);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar pedido.");
		}
	}

	@GetMapping
	@ResponseBody
	@Operation(summary = "Listar todos os pedidos")
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um pedido em específico")
	public ResponseEntity<Pedido> buscarPedido(@PathVariable("id") Long id) {
		try {
			Pedido pedido = pedidoService.buscarPeloId(id);
			return ResponseEntity.ok(pedido);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("/{id}/disco")
	@ResponseBody
	@Operation(summary = "Adicionar um disco ao pedido em um cliente em específico")
	public ResponseEntity<String> adicionarDisco(@PathVariable("id") Long id, @RequestParam Long discoId) {
		try {
			String mensagem = pedidoService.adicionarDisco(id, discoId);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar disco ao pedido.");
		}
	}

	@GetMapping("/{id}/discos")
	@ResponseBody
	@Operation(summary = "Listar os discos do pedido de um cliente em específico")
	public ResponseEntity<List<Disco>> listarDiscos(@PathVariable("id") Long id) {
		try {
			List<Disco> itens = pedidoService.listarDiscoPeloIdPedido(id);
			return ResponseEntity.ok(itens);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}/disco")
	@ResponseBody
	@Operation(summary = "Deletar um disco do pedido em um cliente em específico")
	public ResponseEntity<String> deletarDisco(@PathVariable("id") Long id, @RequestParam Long discoId) {
		try {
			String mensagem = pedidoService.deletarDisco(id, discoId);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar disco do pedido.");
		}
	}

	@PatchMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Finalizar um pedido em específico")
	public ResponseEntity<String> finalizarPedido(@PathVariable("id") Long id) {
		try {
			String mensagem = pedidoService.finalizarPedido(id);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao finalizar pedido.");
		}
	}

}
