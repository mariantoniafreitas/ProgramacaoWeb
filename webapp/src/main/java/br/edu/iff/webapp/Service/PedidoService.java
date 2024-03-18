package br.edu.iff.webapp.Service;

import java.time.LocalDateTime;
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

	public String adicionarPedido(Long id) {
		Cliente c = ClienteRepository.buscarPeloId(id);
		if (c == null) {
			return "Cliente não achado";
		} else {
			if (PedidoRepository.buscarPedidosAbertosPeloId(id).size() == 0) {
				Pedido pedido = new Pedido(c);
				c.adicionarPedido(pedido);
				Pedido p = PedidoRepository.saveAndFlush(pedido);
				return "Registrado no id " + p.getId();
			} else {
				return "O cliente já tem uma compra aberta";
			}
		}
	}

	public String atualizarPedido(Long pedidoId, Long clienteId, LocalDateTime data_hora, double frete,
			double total_pedido) {
		Pedido pedido = buscarPeloId(pedidoId);
		if (pedido == null) {
			return "Pedido não encontrado.";
		} else {
			if (pedido.isConcluido()) {
				return "Pedido já concluído.";
			} else {
				if (clienteId != null) {
					Cliente c = ClienteRepository.buscarPeloId(clienteId);
					if (c == null) {
						return "Cliente não encontrado";
					} else {
						if (data_hora != null) {
							pedido.setDataHora(data_hora);
						}
						if (total_pedido > 0) {
							pedido.setTotalPedido(total_pedido);
						}
						if (frete > -1) {
							pedido.setFrete(frete);
						}
						PedidoRepository.saveAndFlush(pedido);
						return "Pedido atualizado no id " + pedido.getId();
					}

				}

			}
		}
		return "Pedido não encontrado.";
	}

	public String deletarPedido(Long pedidoId) {
		Pedido pedido = buscarPeloId(pedidoId);
		if (pedido == null) {
			return "Pedido não encontrado.";
		} else {
			Cliente cliente = ClienteRepository.buscarPeloId(pedido.getCliente().getId());
			if (cliente == null) {
				return "Cliente não achado";
			} else {
				cliente.deletarPedido(pedido);
				ClienteRepository.saveAndFlush(cliente);
				PedidoRepository.delete(pedido);
				return "Pedido deletado no id " + pedido.getId();
			}
		}
	}

	public List<Pedido> listarPedidos() {
		return PedidoRepository.listarPedidos();
	}

	public String adicionarDisco(Long pedidoId, Long discoId) {
		Pedido pedido = buscarPeloId(pedidoId);
		if (pedido == null) {
			return "Pedido não encontrado.";
		} else {
			if (pedido.isConcluido()) {
				return "Pedido já finalizado.";
			} else {
				Disco disco = DiscoRepository.buscarPeloId(discoId);
				if (disco == null) {
					return "Disco não encontrado.";
				} else {
					boolean discoExiste = pedido.getDiscos().stream().anyMatch(d -> d.getId().equals(discoId));

					if (discoExiste) {
						return "O disco já foi adicionado ao pedido.";
					} else {
						disco.setPedido(pedido);
						pedido.adicionarDisco(disco);
						PedidoRepository.saveAndFlush(pedido);
						return "Disco adicionado ao pedido.";
					}
				}
			}
		}
	}

	public String deletarDisco(Long pedidoId, Long discoId) {
		Pedido pedido = buscarPeloId(pedidoId);
		if (pedido == null) {
			return "Pedido não encontrado.";
		} else {
			if (pedido.isConcluido()) {
				return "Pedido já finalizado.";
			} else {
				Disco disco = DiscoRepository.buscarPeloId(discoId);
				if (disco == null) {
					return "Disco não encontrado.";
				} else {
					boolean discoExiste = pedido.getDiscos().stream().anyMatch(d -> d.getId().equals(discoId));

					if (discoExiste) {
						disco.setPedido(null);
						pedido.deletarDisco(disco);
						PedidoRepository.saveAndFlush(pedido);
						return "Disco deletado do pedido.";
					} else {
						return "O disco não foi encontrado no pedido.";
					}
				}
			}
		}
	}

	public List<Disco> listarDiscoPeloIdPedido(Long id) {
		Pedido pedido = buscarPeloId(id);
		if (pedido != null) {
			return pedido.getDiscos();
		} else {
			return null;
		}
	}

	public Pedido buscarPeloId(Long id) {
		return PedidoRepository.buscarPeloId(id);
	}

	public String finalizarPedido(Long idPedido) {
		Pedido p = buscarPeloId(idPedido);
		if (p == null) {
			return "Pedido não encontrado.";
		} else {
			if (p.isConcluido()) {
				return "Pedido já finalizado.";
			} else {
				if (p.getDiscos().size() == 0) {
					return "O pedido precisa ter no mínimo 1 disco.";
				} else {
					p.concluido();
					PedidoRepository.saveAndFlush(p);
					ClienteRepository.flush();
					return "Pedido finalizado com sucesso.";
				}
			}
		}
	}
}