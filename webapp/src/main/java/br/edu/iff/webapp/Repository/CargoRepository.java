package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.webapp.Entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
