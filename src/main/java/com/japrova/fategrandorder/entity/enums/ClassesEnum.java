package com.japrova.fategrandorder.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassesEnum {

    SABER(1, "Saber"), ARCHER(2, "Archer"), LANCER(3, "Lancer"),
    RIDER(4, "Rider"), CASTER(5, "Caster"), ASSASSIN(6, "Assassin"),
    BERSERKER(7, "Berserker"), RULER(8, "Ruler"), AVENGER(9, "Avenger"),
    MOONCANCER(10, "Moon Cancer");

    private final int id;
    private final String className;
}
