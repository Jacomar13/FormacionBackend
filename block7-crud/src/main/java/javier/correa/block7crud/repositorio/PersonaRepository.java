package javier.correa.block7crud.repositorio;

import javier.correa.block7crud.dominio.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByNombre(String nombre);
}
