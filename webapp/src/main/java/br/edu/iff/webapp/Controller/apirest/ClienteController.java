package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteService;
	
	@PostMapping("")
	@ResponseBody
	@Operation(description = "Adicionar um novo cliente")
	public String addCliente(@RequestBody Cliente cliente) throws Exception {
		return clienteService.addCliente(cliente);
	    //return clienteService.addCliente(new Cliente(cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getSenha(), cliente.getTel(), cliente.getEndereco(), cliente.getDataNascimento(), cliente.getSaldoDisponivel()));
	}


    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "Atualizar um cliente")
    public String atualizarCliente(@PathVariable("id") Long id, String nome, String email, String senha) {
		Cliente cBusca = clienteService.buscarPeloID(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {
			return clienteService.atualizarCliente(cBusca.getCpf(), nome, email, senha);
		}
	}

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Apagar um cliente")
    public String deleteCliente(@PathVariable("id") Long id) {
    	Cliente cBusca = clienteService.buscarPeloID(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {			
			return clienteService.deletarCliente(cBusca.getCpf());
		}
	}

    @GetMapping("/{id}")
    @ResponseBody
    @Operation(description = "Informacoes de um cliente")
    public Cliente buscarClienteId(@PathVariable("id") Long id) {
		return clienteService.buscarPeloID(id);
	}
    
    @GetMapping("")
	@ResponseBody
	@Operation(description = "Listar todos os clientes")
	public List<Cliente> listarClientes() {
		return clienteService.listarClientes();
	}

}
