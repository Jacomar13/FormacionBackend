package javier.correa.block6personcontrollers.servicios;

import javier.correa.block6personcontrollers.objetos.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {


    private ArrayList<Ciudad> ciudades=new ArrayList<>();

    public void crearCiudad (Ciudad ciudad) {
        ciudades.add(ciudad);
    }
    public ArrayList<Ciudad> getCiudades(){
        return ciudades;
    }

    @Override
    public String toString() {
        return "CiudadService{"  + ciudades +
                '}';
    }
}
