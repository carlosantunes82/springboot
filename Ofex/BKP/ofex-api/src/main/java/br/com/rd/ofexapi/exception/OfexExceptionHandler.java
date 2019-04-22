package br.com.rd.ofexapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class OfexExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(OfexExceptionHandler.class);

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity validationError(Exception ex) {
        LOGGER.error("Ocorreu um erro no ofex-api.", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro no ofex-api: \n"
                        + ex.getMessage());
    }
}



