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

@Controller
@RequestMapping(path = "/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@PostMapping("")
	@ResponseBody
	public String addPedido(String cpf) {			
		return pedidoService.addPedido(cpf);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public String atualizarPedido(@PathVariable("id") Long id, String cpf) throws Exception {
		return pedidoService.atualizarPedido(id, cpf);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletarPedido(@PathVariable("id") Long id) {
		return pedidoService.deletarPedido(id);
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Pedido> listarPedidos() throws Exception{
		return pedidoService.listarPedidos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Pedido buscarPedido(@PathVariable("id") Long id) {
		return pedidoService.getPedidoById(id);
	}
	
	@PostMapping("/{id}/discos")
	@ResponseBody
	public String adDisco(@PathVariable("id") String id, String titulo) {
		return pedidoService.addDisco(id, titulo);
	}
	
	@GetMapping("/{id}/e_books")
	@ResponseBody
	public List<Disco> listarDiscos(@PathVariable("id") Long id)  {
		return pedidoService.ListarDiscoPeloIdCompra(id);
	}
	
	@DeleteMapping("/{id}/e_books")
	@ResponseBody
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
