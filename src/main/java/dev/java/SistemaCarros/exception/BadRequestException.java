package dev.java.SistemaCarros.exception;

public class BadRequestException extends org.apache.coyote.BadRequestException {
    public BadRequestException(String mensagem){
        super(mensagem);
    }
}
