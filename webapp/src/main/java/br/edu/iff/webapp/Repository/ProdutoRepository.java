package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.webapp.Entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
