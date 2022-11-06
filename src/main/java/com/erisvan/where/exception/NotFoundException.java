package com.erisvan.where.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Não encontrado.");
    }
}
