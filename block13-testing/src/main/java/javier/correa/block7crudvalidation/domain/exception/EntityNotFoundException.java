package javier.correa.block7crudvalidation.domain.exception;

import lombok.Data;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
@Generated

public class EntityNotFoundException extends RuntimeException{
    private transient CustomError customError;

    public EntityNotFoundException(String mensaje, int httpCode) {
        super(mensaje);
        customError = new CustomError(httpCode, mensaje);
    }

}
