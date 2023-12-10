package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

}
