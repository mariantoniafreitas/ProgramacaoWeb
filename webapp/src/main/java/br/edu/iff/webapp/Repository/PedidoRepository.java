package br.edu.iff.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query(value="SELECT * FROM PEDIDO WHERE ID = ?1", nativeQuery = true)
	Pedido buscarPeloId(Long id);
	
	@Query(value="SELECT * FROM PEDIDO  WHERE CPF_CLIENTE = ?1", nativeQuery = true)
	List<Pedido> buscarPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM PEDIDO  WHERE CLIENTE_ID = ?1 AND CONCLUIDO = FALSE", nativeQuery = true)
	List<Pedido> buscarPedidosAbertosPeloId(Long id);
	
	@Query(value = "SELECT * FROM PEDIDO", nativeQuery = true)
	List<Pedido> listarPedidos();
}