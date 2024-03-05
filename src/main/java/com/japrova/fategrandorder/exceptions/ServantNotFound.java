package com.japrova.fategrandorder.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class ServantNotFound extends RuntimeException {

    public ServantNotFound(String message) {
        super(message);
    }

    public ServantNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ServantNotFound(Throwable cause) {
        super(cause);
    }
}
