package br.ufrn.myway.Model.DTO;

import org.springframework.http.HttpStatus;

public class ExceptionDTO {
    private HttpStatus status;
    private String message;

    public ExceptionDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
