package com.japrova.fategrandorder.dto;


import com.japrova.fategrandorder.entity.CardTypes;

import java.util.Set;

public record ServantDto(int idServant, String nameServant, String noblePhantasm, String servantClass,
                         Set<CardTypeDto> cardTypes) {

}

