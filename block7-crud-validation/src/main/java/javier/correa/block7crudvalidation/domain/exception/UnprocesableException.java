package javier.correa.block7crudvalidation.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocesableException extends RuntimeException{
    public UnprocesableException(String mensaje){
        super(mensaje);
    }
}
