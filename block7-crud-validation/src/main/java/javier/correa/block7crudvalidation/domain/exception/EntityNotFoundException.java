package javier.correa.block7crudvalidation.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    private final CustomError customError;

    public EntityNotFoundException(String message, int httpCode) {
        super(message);
        customError = new CustomError(httpCode, message);
    }




}
