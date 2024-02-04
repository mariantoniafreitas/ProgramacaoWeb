package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Repository.ClienteRepository;
import br.edu.iff.webapp.Repository.DiscoRepository;
import br.edu.iff.webapp.Repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository PedidoRepository;
	@Autowired
	private ClienteRepository ClienteRepository;

	
	@Autowired
	private DiscoRepository DiscoRepository;
	
	public String addPedido(String cpf) {
		Cliente cl = ClienteRepository.buscarPeloCPF(cpf);
		if(cl==null) {
			return "Cliente não achado";
		}else {
			if(PedidoRepository.BuscarPedidosAbertosPeloCPF(cpf).size()==0) {				
				Pedido pedido = new Pedido(cpf);
				cl.addPedido(pedido);
				Pedido p = PedidoRepository.save(pedido);
				ClienteRepository.flush();
				return "Registrado no id "+p.getId();
			}else {
				return "O cliente já tem uma compra aberta";
			}
		}
	}
	
	public String atualizarPedido(Long idPedido, String cpf) {
		Pedido pedido = PedidoRepository.BuscarPeloId(idPedido);
		if(pedido==null) {
			return "Compra não achada";
		}else {		
			if(pedido.isConcluido()) {
				return "Compra já finalizada";
			}else {				
				if(cpf!=null) {				
					Cliente cl = ClienteRepository.buscarPeloCPF(cpf);
					if(cl==null) {
						return "Cliente não achado";
					}else {
						pedido.setCpfCliente(cpf);
						cl.addPedido(pedido);
						ClienteRepository.flush();
					}
				}
				ClienteRepository.flush();
				return "Atualizado no id "+pedido.getId();
			}
		}
	}
	
	public String deletarPedido(Long idPedido) {
		Pedido pedido = PedidoRepository.BuscarPeloId(idPedido);
		if(pedido==null) {
			return "Compra não achada";
		}else {		
			Long idCliente = PedidoRepository.BuscarPeloIdCliente(idPedido);
			Cliente cliente = ClienteRepository.BuscarPeloId(idCliente);
			if(cliente==null) {
				return "Cliente não achado";
			}else {
				cliente.deletePedido(pedido);
				ClienteRepository.flush();
			}

			PedidoRepository.delete(pedido);
			return "Deletado no id "+pedido.getId();
		}
	}
	
	public List<Pedido> listarPedidos() throws Exception {
		return PedidoRepository.findAll();
	}
	
	public String addDisco(String idPedido, String titulo) {
		Pedido pedido = PedidoRepository.BuscarPeloId(Long.parseLong(idPedido));
		if(pedido==null) {
			return "Pedido não encontrado";
		}else {			
			if(pedido.isConcluido()) {
				return "Pedido já finalizado";
			}else {				
				Disco disco = DiscoRepository.buscarPeloTitulo(titulo);
				if(disco==null) {
					return "Disco não encontrado";
				}else {
					if(PedidoRepository.verificarProdutoPedido(disco.getId(),pedido.getId())!=0) {
						return "Disco já cadastrado";
					}else {					
						pedido.addProduto(disco);
						PedidoRepository.flush();
						return "Disco adicionado";
					}
				}
			}
		}
	}
	
	public String removeDisco(String idPedido, String titulo) {
		Pedido pedido = PedidoRepository.BuscarPeloId(Long.parseLong(idPedido));
		if(pedido==null) {
			return "Pedido não encontrado";
		}else {		
			Disco disco = DiscoRepository.buscarPeloTitulo(titulo);
			if(disco==null) {
				return "Disco não encontrado";
			}else {
				if(PedidoRepository.verificarProdutoPedido(disco.getId(),pedido.getId())==0) {
					return "Disco não consta na compra";
				}else {
					pedido.deleteProduto(disco);
					PedidoRepository.flush();
					return "Disco removido";
				}
			}
		}
	}
	
	public List<Disco> ListarDiscoPeloIdCompra(Long id){
		return DiscoRepository.ListarDiscoPeloIdCompra(id);
	}
	
	public Pedido getPedidoById(Long id) {
		return PedidoRepository.BuscarPeloId(id);
	}
	
	public List<Pedido> buscarPeloCPFCliente(String cpf) {
		return PedidoRepository.BuscarPeloCPF(cpf);
	}
	
	
	public String finalizarPedidoPeloId(Long idPedido) {
		Pedido c = PedidoRepository.BuscarPeloId(idPedido);
		if(c==null) {
			return "Compra não encontrada";
		}else {	
			if(c.isConcluido()) {
				return "Compra já finalizada";
			}else {
				if(c.getQtdProdutos()==0) {
					return "A compra precisa ter no mínimo 1 produto.";
				}else {
						c.concluirPedido();
						PedidoRepository.flush();
						ClienteRepository.flush();
						return "Compra finalizada com sucesso";
					}
				}
			}
		}
	
	public Pedido PedidoAbertaPeloCPFCliente(String cpf) {
		List<Pedido> compra = PedidoRepository.BuscarPedidosAbertosPeloCPF(cpf);
		if(compra.size()==0) {
			this.addPedido(cpf);
			compra = PedidoRepository.BuscarPedidosAbertosPeloCPF(cpf);
		}
		return compra.get(0);
	}
	
	public List<Pedido> ListarFechadasPeloCPFCliente(String cpf) {
		return PedidoRepository.BuscarPedidosFechadosPeloCPF(cpf);
	}
	
}
