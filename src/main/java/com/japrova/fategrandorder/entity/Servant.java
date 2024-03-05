package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servants")
public class Servant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servant")
    private int idServant;

    @Column(name = "name_servant")
    private String nameServant;

    @Column(name = "noble_phantasm")
    private String noblePhantasm;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "classes_servants",
            joinColumns = @JoinColumn(name = "id_servant"),
            inverseJoinColumns = @JoinColumn(name = "id_class"))
    private Classes servantClass;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "servants_types",
            joinColumns = @JoinColumn(name = "id_servant"),
            inverseJoinColumns = @JoinColumn(name = "letter_type"))
    private LettersTypes lettersTypes;



    public Servant() {
    }

    public int getIdServant() {
        return idServant;
    }

    public void setIdServant(int idServant) {
        this.idServant = idServant;
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

    public Classes getServantClass() {
        return servantClass;
    }

    public void setServantClass(Classes servantClass) {
        this.servantClass = servantClass;
    }

    public LettersTypes getLettersTypes() {
        return lettersTypes;
    }

    public void setLettersTypes(LettersTypes lettersTypes) {
        this.lettersTypes = lettersTypes;
    }
}