package javier.correa.block7crud.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String edad;
    private  String poblacion;

    public Persona(int id) {
        this.id = id;
    }

    public Persona(PersonaInputDto personaInputDto){
        this.id = personaInputDto.getId();
        this.nombre = personaInputDto.getNombre();
        this.edad = personaInputDto.getEdad();
        this.poblacion = personaInputDto.getPoblacion();
    }

    public PersonaOutputDto personaToOutputDto(){
        return new PersonaOutputDto(this.id, this.nombre, this.edad, this.poblacion);
    }

}
