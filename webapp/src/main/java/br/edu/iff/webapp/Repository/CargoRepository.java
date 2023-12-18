package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.webapp.Entities.Cargo;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
