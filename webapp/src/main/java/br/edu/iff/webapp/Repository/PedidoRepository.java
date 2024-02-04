package br.edu.iff.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query(value="SELECT * FROM PEDIDO WHERE ID = ?1", nativeQuery = true)
	Pedido BuscarPeloId(Long id);
	
	@Query(value="SELECT ID_CLIENTE FROM PEDIDO WHERE ID = ?1", nativeQuery = true)
	Long BuscarPeloIdCliente(Long id);
	
	@Query(value="SELECT COUNT(*) PEDIDO_PRODUTO JOIN PEDIDO WHERE FK_PRODUTO = ?1 AND FK_PEDIDO = ?2", nativeQuery = true)
	int verificarProdutoPedido(Long idProduto, Long idPedido);
	
	@Query(value="SELECT * FROM PEDIDO  WHERE CPF_CLIENTE = ?1", nativeQuery = true)
	List<Pedido> BuscarPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM PEDIDO  WHERE CPF_CLIENTE = ?1 AND CONCLUIDO = FALSE", nativeQuery = true)
	List<Pedido> BuscarPedidosAbertosPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM PEDIDO  WHERE CPF_CLIENTE = ?1 AND CONCLUIDO = TRUE", nativeQuery = true)
	List<Pedido> BuscarPedidosFechadosPeloCPF(String cpf);

	@Query(value="SELECT * FROM PEDIDO WHERE CONCLUIDO = FALSE AND ID IN(SELECT FK_PEDIDO FROM PEDIDO_PRODUTO WHERE FK_PRODUTO = ?1)", nativeQuery = true)
	List<Pedido> BuscarPedidosPeloIdProduto(Long id);

}
