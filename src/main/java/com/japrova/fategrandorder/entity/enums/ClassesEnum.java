package com.japrova.fategrandorder.entity.enums;

public enum ClassesEnum {

    SABER(1, "Saber"), ARCHER(2, "Archer"), LANCER(3, "Lancer"),
    RIDER(4, "Rider"), CASTER(5, "Caster"), ASSASSIN(6, "Assassin"),
    BERSERKER(7, "Berserker"), RULER(8, "Ruler"), AVENGER(9, "Avenger"),
    MOONCANCER(10, "Moon Cancer");

    private final int id;
    private final String className;

    ClassesEnum(int id, String className) {
        this.id = id;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}
