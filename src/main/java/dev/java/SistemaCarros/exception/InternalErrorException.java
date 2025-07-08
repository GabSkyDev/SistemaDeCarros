package dev.java.SistemaCarros.exception;

import java.util.concurrent.ExecutionException;

public class InternalErrorException extends ExecutionException {
    public InternalErrorException(String mensagem){
        super(mensagem);
    }
}
