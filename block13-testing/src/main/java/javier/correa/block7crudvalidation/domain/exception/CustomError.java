package javier.correa.block7crudvalidation.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CustomError {
    private Date timestamp;
    private String mensaje;
    private int httpCode;

    public CustomError(int httpCode, String mensaje) {
        this.timestamp = new Date();
        this.httpCode = httpCode;
        this.mensaje = mensaje;
    }
}
