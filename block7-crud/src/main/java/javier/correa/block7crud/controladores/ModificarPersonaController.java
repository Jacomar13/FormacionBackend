package javier.correa.block7crud.controladores;

import jakarta.persistence.EntityNotFoundException;
import javier.correa.block7crud.aplicaciones.PersonaServiceImpl;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import javier.correa.block7crud.dominio.Persona;
import javier.correa.block7crud.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/persona")
public class ModificarPersonaController {

}
