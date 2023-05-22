package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByUsuario(String username);

}
