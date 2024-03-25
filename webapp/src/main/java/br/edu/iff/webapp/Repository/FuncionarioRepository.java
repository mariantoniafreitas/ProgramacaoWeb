package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query(value="SELECT * FROM FUNCIONARIO WHERE CPF = ?1", nativeQuery = true)
	Funcionario buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE ID = ?1", nativeQuery = true)
	Funcionario BuscarPeloId(Long id);
	
	@Query(value="SELECT MAX(NIVEL_ACESSO) FROM FUNCIONARIO JOIN CARGO ON CARGO.ID = FK_CARGO", nativeQuery = true)
	String maiorNivelAcesso();
}
