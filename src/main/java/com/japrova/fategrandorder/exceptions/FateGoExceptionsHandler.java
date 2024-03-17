package com.japrova.fategrandorder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FateGoExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<FgoException> handleExceptionServant(ServantNotFound snf) {

        FgoException fgoException = new FgoException();

        fgoException.setStatus(HttpStatus.NOT_FOUND.value());
        fgoException.setMessage(snf.getMessage());
        fgoException.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(fgoException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DataException> handleExceptionClasses(ErrorPersistence de) {

        DataException dataException = new DataException();

        dataException.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataException.setMessage(de.getMessage());
        dataException.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(dataException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}