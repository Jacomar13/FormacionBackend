package javier.correa.block7crudvalidation.domain;

import java.util.Date;

public class CustomResponse {
    private Date timestamp;
    private String mensaje;
    private int httpCode;

    public CustomResponse(Date timestamp, String mensaje, int httpCode) {
        super();
        this.timestamp = timestamp;
        this.mensaje = mensaje;
        this.httpCode=httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }
}
