package javier.correa.block7crud.controladores;

import javier.correa.block7crud.aplicaciones.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class BorrarPersonaController {
    @Autowired
    PersonaServiceImpl personaService;

}
