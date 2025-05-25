package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.DTO.ExceptionDTO;
import br.ufrn.myway.Service.BusinessException;
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

}
