package javier.correa.block13mongodb.repositories;


import java.util.Arrays;
import java.util.List;

import javier.correa.block13mongodb.application.MongoDBConfig;
import javier.correa.block13mongodb.domain.Persona;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoTemplateTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MongoDBConfig.class);
        ctx.refresh();
        MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);

        mongoTemplate.dropCollection(Persona.class);

        Persona ram = new Persona(101, "Ram", 19);
        Persona shyam = new Persona(102, "Shyam", 19);
        Persona mohan = new Persona(103, "Mohan", 20);
        mongoTemplate.insert(Arrays.asList(ram, shyam, mohan), Persona.class);

        Query query = new Query();
        query.addCriteria(Criteria.where("age").is(19));
        List<Persona> list = mongoTemplate.find(query, Persona.class, "student");
        list.forEach(std -> System.out.println(std.getName() + " - " + std.getAge()));

        ctx.registerShutdownHook();
        ctx.close();
    }
}
