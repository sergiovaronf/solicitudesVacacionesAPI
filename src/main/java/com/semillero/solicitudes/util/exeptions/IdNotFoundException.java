package com.semillero.solicitudes.util.exeptions;

public class IdNotFoundException extends RuntimeException{

    private static final String errorMessage = "El id no existe en %s";

    public IdNotFoundException(String tableName){
        super(String.format(errorMessage, tableName));
    }

}
