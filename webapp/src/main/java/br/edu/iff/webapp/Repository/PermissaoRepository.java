package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	@Query(value="SELECT * FROM PERMISSAO WHERE ACESSO = ?1", nativeQuery = true)
	public Permissao getByAcesso(String acesso);
	
}
