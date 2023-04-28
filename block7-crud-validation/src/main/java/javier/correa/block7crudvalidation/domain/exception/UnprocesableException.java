package javier.correa.block7crudvalidation.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocesableException extends RuntimeException{
    private final CustomError customError;
    public UnprocesableException(String mensaje, int httpcode){
        super(mensaje);
        customError = new CustomError(httpcode, mensaje);
    }
}
