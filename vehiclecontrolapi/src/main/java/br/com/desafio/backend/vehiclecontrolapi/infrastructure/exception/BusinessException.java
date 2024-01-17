package br.com.desafio.backend.vehiclecontrolapi.infrastructure.exception;

public class BusinessException extends RuntimeException{
    
    public BusinessException(String msg){
        super(msg);
    }
}
