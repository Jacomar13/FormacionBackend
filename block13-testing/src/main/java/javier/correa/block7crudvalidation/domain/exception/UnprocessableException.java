package javier.correa.block7crudvalidation.domain.exception;

import lombok.Data;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Generated

public class UnprocessableException extends RuntimeException{
    private transient CustomError customError;
    public UnprocessableException(String mensaje, int httpcode){
        super(mensaje);
        customError = new CustomError(httpcode, mensaje);
    }
}
