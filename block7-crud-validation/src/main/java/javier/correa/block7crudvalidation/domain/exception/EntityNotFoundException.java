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

    public CustomError getCustomError() {
        return customError;
    }

    public static class CustomError {
        private final Date timestamp;
        private final int httpCode;
        private final String mensaje;

        public CustomError(int httpCode, String mensaje) {
            this.timestamp = new Date();
            this.httpCode = httpCode;
            this.mensaje = mensaje;
        }
    }
}
