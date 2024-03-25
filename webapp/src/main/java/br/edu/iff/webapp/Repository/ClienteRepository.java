package br.edu.iff.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value="SELECT * FROM CLIENTE WHERE CPF = ?1", nativeQuery = true)
	Cliente buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM CLIENTE WHERE ID = ?1", nativeQuery = true)
	Cliente buscarPeloId(Long id);
	
	@Query(value="SELECT CLIENTE.* FROM CLIENTE, PEDIDO WHERE ID_CLIENTE=CLIENTE.ID AND PEDIDO.ID = ?1", nativeQuery = true)
	Cliente buscarPeloIdPedido(Long id);
	
	@Query(value = "SELECT * FROM CLIENTE", nativeQuery = true)
	List<Cliente> listarClientes();
	
    @Query(value = "SELECT * FROM Cliente WHERE fk_pessoa = (SELECT id FROM Usuario WHERE login = :login)", nativeQuery = true)
    Cliente buscarPorLogin(String login);
}

