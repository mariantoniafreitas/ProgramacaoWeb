package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value="SELECT * FROM CLIENTE WHERE CPF = ?1", nativeQuery = true)
	Cliente buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM CLIENTE WHERE ID = ?1", nativeQuery = true)
	Cliente BuscarPeloId(Long id);
	
	@Query(value="SELECT CLIENTE.* FROM CLIENTE, COMPRA WHERE ID_CLIENTE=CLIENTE.ID AND COMPRA.ID = ?1", nativeQuery = true)
	Cliente BuscarPeloIdCompra(Long id);
}
