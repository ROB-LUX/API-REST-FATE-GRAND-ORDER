package com.japrova.fategrandorder.dto;

public class ServantDto {

    private String nameServant;

    private String noblePhantasm;

    private String servantClass;

    private String letterType;

    public ServantDto() {
    }

    public ServantDto(String nameServant, String noblePhantasm, String servantClass, String letterType) {
        this.nameServant = nameServant;
        this.noblePhantasm = noblePhantasm;
        this.servantClass = servantClass;
        this.letterType = letterType;
    }

    public String getNameServant() {
        return nameServant;
    }

    public void setNameServant(String nameServant) {
        this.nameServant = nameServant;
    }

    public String getNoblePhantasm() {
        return noblePhantasm;
    }

    public void setNoblePhantasm(String noblePhantasm) {
        this.noblePhantasm = noblePhantasm;
    }

    public String getServantClass() {
        return servantClass;
    }

    public void setServantClass(String servantClass) {
        this.servantClass = servantClass;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }
}