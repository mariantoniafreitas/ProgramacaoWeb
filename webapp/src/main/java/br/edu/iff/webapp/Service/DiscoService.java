package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Entities.Pedido;
import br.edu.iff.webapp.Repository.DiscoRepository;
import br.edu.iff.webapp.Repository.PedidoRepository;

@Service
public class DiscoService {
	
	@Autowired
	private DiscoRepository DiscoRepository;
	@Autowired
	private PedidoRepository PedidoRepository;
	
	public String addDisco(Disco disco) {
		if(DiscoRepository.buscarPeloTitulo(disco.getTitulo())!=null) {
			return "Disco já cadastrado";
		}else{
			Disco d1 = DiscoRepository.save(disco);
			return "Registrado no id "+d1.getId();
		}
	}
	
	public String atualizarDisco(String titulo, double valor, String genero, String interprete, String gravadora, int totalMusicas, double tempoDuracao) {
		Disco disco = DiscoRepository.buscarPeloTitulo(titulo);
		if(disco==null) {
			return "Disco não achado";
		}else {		
			if(valor>=0) {
				double diferencaPreco = disco.getValor() - valor;
				List<Pedido> pedidos = PedidoRepository.BuscarPedidosPeloIdProduto(disco.getId());
				for(int i=0;i<pedidos.size();i++) {
					pedidos.get(i).setTotalPedido(pedidos.get(i).getTotalPedido()-diferencaPreco);
				}
				disco.setValor(valor);
			}
			if(totalMusicas>=0) {				
				disco.setTotalMusicas(totalMusicas);
			}
			if(genero!=null) {				
				disco.setGenero(genero);
			}
			if(interprete!=null) {				
				disco.setInterprete(interprete);
			}
			if(gravadora!=null) {				
				disco.setGravadora(gravadora);
			}
			if(tempoDuracao > 0) {
				disco.setTempoDuracao(tempoDuracao);
			}
			DiscoRepository.flush();
			return "Atualizado no id "+disco.getId();
		}
	}
	
	public String deletarDiscoTitulo(String titulo) {
		Disco disco = DiscoRepository.buscarPeloTitulo(titulo);
		if(disco!=null) {	
			List<Pedido> pedidos = PedidoRepository.BuscarPedidosPeloIdProduto(disco.getId());
			for(int i=0;i<pedidos.size();i++) {
				pedidos.get(i).deleteProduto(disco);
			}			
			DiscoRepository.delete(disco);
			return "Disco deletado no id "+disco.getId();				
		}else {
			return "Disco não encontrado";
		}
	}
	
	public List<Disco> listarDiscos() throws Exception {
		return DiscoRepository.findAll();
	}

	//pelotitulo
	public Disco buscarDisco(String titulo) {
		return DiscoRepository.buscarPeloTitulo(titulo);
	}
	
	public Disco getDiscoById(Long id) {
		return DiscoRepository.BuscarPeloId(id);
	}
	
	
	
	
	
	
	
	
	
	
}
