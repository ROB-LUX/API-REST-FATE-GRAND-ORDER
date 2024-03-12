package com.japrova.fategrandorder.exceptions;

public class DataNotFound extends RuntimeException {

    public DataNotFound(String message) {
        super(message);
    }

    public DataNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFound(Throwable cause) {
        super(cause);
    }
}
