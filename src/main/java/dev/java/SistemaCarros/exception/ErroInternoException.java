package dev.java.SistemaCarros.exception;

import java.util.concurrent.ExecutionException;

public class ErroInternoException extends ExecutionException {
    public ErroInternoException(String mensagem){
        super(mensagem);
    }
}
