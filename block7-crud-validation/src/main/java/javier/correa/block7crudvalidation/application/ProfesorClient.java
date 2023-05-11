package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url = "http://localhost:8081", name = "profesorCliente")
public interface ProfesorClient {
    @GetMapping("/profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id);
}
