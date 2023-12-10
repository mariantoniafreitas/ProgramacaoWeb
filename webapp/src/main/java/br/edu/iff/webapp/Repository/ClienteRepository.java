package br.edu.iff.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.webapp.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
