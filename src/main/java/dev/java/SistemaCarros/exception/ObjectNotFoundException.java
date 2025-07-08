package dev.java.SistemaCarros.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String mensagem){
        super(mensagem);
    }
}
