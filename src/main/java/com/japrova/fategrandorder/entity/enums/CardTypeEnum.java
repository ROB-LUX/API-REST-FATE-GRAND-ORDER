package com.japrova.fategrandorder.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum CardTypeEnum {

    BUSTER(1, "BUSTER"), ARTS(2, "ARTS"), QUICK(3, "QUICK");

    private final int id;
    private final String card;
}
