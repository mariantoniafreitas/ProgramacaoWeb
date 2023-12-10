package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.webapp.Entities.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
