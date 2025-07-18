package br.ufrn.myway.coldspots.Common.controller;

import br.ufrn.myway.model.DTO.ExceptionDTO;
import br.ufrn.myway.coldspots.Exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDTO> handleBusinessException(BusinessException e) {
        return ResponseEntity.status(e.getStatus()).body(
                new ExceptionDTO(e.getStatus(), e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception e) {
        return ResponseEntity.status(500).body(
                new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())
        );
    }


}
