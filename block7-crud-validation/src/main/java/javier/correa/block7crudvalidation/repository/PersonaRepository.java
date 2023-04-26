package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<List<Persona>> findByUsuario(String user);
}
