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

	public String adicionarCliente(String login, String senha, String nome, String email, String cpf, String tel,
			String endereco, String dataNascimento) {
		if (ClienteRepository.buscarPeloCPF(cpf) == null) {
			Usuario usuario = UsuarioService.adicionarUsuario(login, senha, 0);
			if (usuario != null) {
				Cliente c = new Cliente(nome, email, cpf, tel, endereco, dataNascimento);
				c.setUsuario(usuario);
				ClienteRepository.save(c);
				return "Cliente registrado com sucesso";
			}
			return "Login já existe.";
		}
		return "Cliente já cadastrado neste CPF.";
	}

	public String atualizarCliente(Long id, String nome, String email, String cpf, String tel, String endereco,
			String dataNascimento) {
		Cliente c = buscarPeloId(id);
		if (c == null) {
			return "Cliente não encontrado.";
		} else {
			if (nome != null) {
				c.setNome(nome);
			}
			if (email != null) {
				c.setEmail(email);
			}
			if (cpf != null) {
				c.setCpf(cpf);
			}
			if (tel != null) {
				c.setTel(tel);
			}
			if (endereco != null) {
				c.setEndereco(endereco);
			}
			if (dataNascimento != null) {
				c.setDataNascimento(dataNascimento);
			}
			ClienteRepository.saveAndFlush(c);
			return "Cliente atualizado no id" + c.getId();
		}
	}

	public String deletarCliente(Long id) {
		Cliente c = buscarPeloId(id);
		if (c != null) {
			List<Pedido> pedidos = PedidoRepository.buscarPedidosAbertosPeloId(id);
			for (int i = 0; i < pedidos.size(); i++) {
				PedidoRepository.delete(pedidos.get(i));
			}
			ClienteRepository.delete(c);
			return "Cliente deletado no id " + c.getId();
		} else {
			return "Cliente não encontrado";
		}
	}

	public List<Cliente> listarClientes() {
		return ClienteRepository.listarClientes();
	}

	public Cliente buscarClienteCPF(String cpf) {
		return ClienteRepository.buscarPeloCPF(cpf);
	}

	public Cliente buscarPeloId(Long id) {
		return ClienteRepository.buscarPeloId(id);
	}
}
