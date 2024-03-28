package com.japrova.fategrandorder.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExceptionParent {

    private int status;

    private String message;

    private long timeStamp;
}
