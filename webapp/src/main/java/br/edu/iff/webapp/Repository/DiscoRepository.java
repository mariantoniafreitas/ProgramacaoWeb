package br.edu.iff.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Disco;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
	
	@Query(value="SELECT * FROM DISCO WHERE TITULO = ?1", nativeQuery = true)
	Disco buscarPeloTitulo(String Titulo);
	
	@Query(value="SELECT * FROM DISCO WHERE ID = ?1", nativeQuery = true)
	Disco BuscarPeloId(Long id);
	
	@Query(value="SELECT D.* FROM DISCO D, PEDIDO_PRODUTO PP WHERE E.ID = PP.FK_PRODUTO AND PP.FK_PEDIDO = ?1", nativeQuery = true)
	List<Disco> ListarDiscoPeloIdCompra(Long id);
}

