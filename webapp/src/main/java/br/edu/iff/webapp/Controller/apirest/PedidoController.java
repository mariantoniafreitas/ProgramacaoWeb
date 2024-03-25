package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	@ResponseBody
	@Operation(description = "Adicionar um pedido")
	public String addPedido(String cpf) {
		return pedidoService.addPedido(cpf);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(description = "Atualizar um pedido")
	public String atualizarPedido(@PathVariable("id") Long id, String cpf) throws Exception {
		return pedidoService.atualizarPedido(id, cpf);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(description = "Deletar um pedido")
	public String deletarPedido(@PathVariable("id") Long id) {
		return pedidoService.deletarPedido(id);
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(description = "Listar todos os pedidos")
	public List<Pedido> listarPedidos() throws Exception{
		return pedidoService.listarPedidos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(description = "Informacoes de um pedido")
	public Pedido buscarPedido(@PathVariable("id") Long id) {
		return pedidoService.getPedidoById(id);
	}
	
	@PostMapping("/{id}/discos")
	@ResponseBody
	@Operation(description = "Adicionar um disco em um pedido")
	public String adDisco(@PathVariable("id") String id, String titulo) {
		return pedidoService.addDisco(id, titulo);
	}
	
	@GetMapping("/{id}/discos")
	@ResponseBody
	@Operation(description = "Listar todos os discos de um pedido")
	public List<Disco> listarDiscos(@PathVariable("id") Long id)  {
		return pedidoService.ListarDiscoPeloIdCompra(id);
	}
	
	@DeleteMapping("/{id}/discos")
	@ResponseBody
	@Operation(description = "Remover um disco de um pedido")
	public String removeDisco(@PathVariable("id") String id, String titulo) {
		return pedidoService.removeDisco(id, titulo);
	}
	
//	@PatchMapping("/{id}")
//	@ResponseBody
//	@Operation(summary = "Finalizar uma compra em expecifíco")
//	public String finalizarCompra(@PathVariable("id") Long id) throws Exception {
//		return CompraServ.finalizarCompraPeloId(id);
//	}

}
