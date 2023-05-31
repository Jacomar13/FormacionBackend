package javier.correa.block13mongodb.application;

import javier.correa.block13mongodb.controller.dto.PersonaInputDto;
import javier.correa.block13mongodb.controller.dto.PersonaOutputDto;
import javier.correa.block13mongodb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public class PersonaServiceImpl implements PersonaService {
    private final MongoTemplate mongoTemplate;
    @Autowired
    public PersonaServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public PersonaOutputDto savePerson(PersonaInputDto persona) {

        return mongoTemplate.save(new Persona(persona)).personaToOutputDto();
    }
    @Override
    public List<Persona> getAllPerson() {
        return mongoTemplate.findAll(Persona.class);
    }
    @Override
    public List<Persona> getAllPersonPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip(pageNumber * pageSize);
        query.limit(pageSize);

        return mongoTemplate.find(query, Persona.class);
    }
    @Override
    public Persona findOneByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Persona.class);
    }
    @Override
    public List<Persona> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Persona.class);
    }

    @Override
    public PersonaOutputDto updateOnePerson(String personId, PersonaInputDto persona) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(personId));
        Persona personaEncontrada = mongoTemplate.findOne(query, Persona.class);
        
        if (personaEncontrada == null) {
            throw new RuntimeException("Persona no encontrada");
        } else {
            personaEncontrada.setName(persona.getName());
            personaEncontrada.setAge(persona.getAge());
            personaEncontrada.setDateOfBirth(persona.getDateOfBirth());
            personaEncontrada.setFavoriteBooks(persona.getFavoriteBooks());
            Persona personaActualizada = mongoTemplate.save(personaEncontrada);
            return personaActualizada.personaToOutputDto();
        }
    }
    @Override
    public void deletePerson(PersonaInputDto persona) {
        mongoTemplate.remove(persona);
    }

}

