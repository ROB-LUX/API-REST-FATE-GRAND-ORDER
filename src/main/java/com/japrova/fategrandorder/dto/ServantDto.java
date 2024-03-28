package com.japrova.fategrandorder.dto;


import com.japrova.fategrandorder.entity.CardTypes;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public record ServantDto(int idServant, String nameServant, String noblePhantasm, String servantClass,
                         List<CardTypeDto> cardTypeDto) {

}

