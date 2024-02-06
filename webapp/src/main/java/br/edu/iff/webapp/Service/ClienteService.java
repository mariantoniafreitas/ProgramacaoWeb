package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Repository.ClienteRepository;
import br.edu.iff.webapp.Repository.PedidoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;
	@Autowired 
	private PedidoRepository PedidoRepository;
	@Autowired
	private UsuarioService UsuarioService;

	public String addCliente(Cliente cliente) {
		if(ClienteRepository.buscarPeloCPF(cliente.getCpf())!=null) {
			return "Cliente já cadastrado";
		}else{
			Usuario usuario = UsuarioService.salvar(cliente.getCpf(), cliente.getSenha(), "Cliente");
			cliente.setUsuario(usuario);
			ClienteRepository.save(cliente);
			return "Registrado com sucesso";
		}
	}
	
	public String atualizarCliente (String cpf, String nome, String email, String senha) {
		Cliente c = ClienteRepository.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado.";
		} else {
			if(nome!=null) {
				c.setNome(nome);
			}
			if(email!=null) {
				c.setEmail(email);
			}
			if(senha!=null) {
				c.setSenha(senha);
				UsuarioService.atualizarSenha(c.getUsuario(), senha);
			}
			ClienteRepository.flush();
			return "Atualizado no id" +c.getId();
		}
	}
	
	public String deletarCliente(String cpf) {
		Cliente c = ClienteRepository.buscarPeloCPF(cpf);
		if(c!=null) {
			List<Pedido> pedidos = PedidoRepository.BuscarPedidosAbertosPeloCPF(cpf);
			for(int i=0;i<pedidos.size();i++) {
				PedidoRepository.delete(pedidos.get(i));
			}
			ClienteRepository.delete(c);
			return "Cliente deletado no id "+c.getId();
		}else {
			return "Cliente não encontrado";
		}
	}
	
	public List<Cliente> listarClientes(){
		return ClienteRepository.findAll();
	}
	
	public Cliente buscarClienteCPF(String cpf) {
		return ClienteRepository.buscarPeloCPF(cpf);
	}
	
	public Cliente buscarPeloID(Long id) {
		return ClienteRepository.BuscarPeloId(id);
	}
}
