package br.edu.iff.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query(value="SELECT * FROM FUNCIONARIO WHERE CPF = ?1", nativeQuery = true)
	Funcionario buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE ID = ?1", nativeQuery = true)
	Funcionario buscarPeloId(Long id);

	@Query(value = "SELECT * FROM FUNCIONARIO", nativeQuery = true)
	List<Funcionario> listarFuncionarios();
}
