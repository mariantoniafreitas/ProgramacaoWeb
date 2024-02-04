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

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Service.ClienteService;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteService;

    @PostMapping("")
    @ResponseBody
    public String addCliente(String nome, String email, String cpf, String senha, String telefone, String endereco, String dataNascimento) throws Exception {			
		return clienteService.addCliente(new Cliente(nome, email, cpf, senha, telefone, endereco, dataNascimento));
	}
    @PutMapping("/{id}")
    @ResponseBody
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
    public Cliente buscarClienteId(@PathVariable("id") Long id) {
		return clienteService.buscarPeloID(id);
	}
    
    @GetMapping("")
	@ResponseBody
	public List<Cliente> listarClientes() {
		return clienteService.listarClientes();
	}

}
