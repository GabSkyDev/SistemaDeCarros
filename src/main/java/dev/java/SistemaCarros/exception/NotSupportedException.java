package dev.java.SistemaCarros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NotSupportedException extends RuntimeException {
    public NotSupportedException(String mensagem){
        super(mensagem);
    }
}
