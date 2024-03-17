package com.japrova.fategrandorder.exceptions;

import jakarta.persistence.PersistenceException;


public class ErrorPersistence extends PersistenceException {

    public ErrorPersistence(String message) {
        super(message);
    }

    public ErrorPersistence(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorPersistence(Throwable cause) {
        super(cause);
    }
}
