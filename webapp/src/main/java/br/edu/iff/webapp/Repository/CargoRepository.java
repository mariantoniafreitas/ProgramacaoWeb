package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Cargo;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
	
	@Query(value="SELECT * FROM CARGO WHERE DESCRICAO = ?1", nativeQuery = true)
	Cargo buscarPelaDescricao(String descricao);
	
	@Query(value="SELECT * FROM CARGO WHERE ID = ?1", nativeQuery = true)
	Cargo BuscarPeloId(Long id);
	
	@Query(value="SELECT COUNT(*) FROM FUNCIONARIO JOIN CARGO WHERE DESCRICAO = ?1 AND FK_CARGO = CARGO.ID", nativeQuery = true)
	int BuscarFuncionariosPeloCargo(String descricao);
}
