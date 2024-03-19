package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servants")
@Getter @Setter @NoArgsConstructor
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

    public Servant(int idServant, String nameServant, String noblePhantasm) {
        this.idServant = idServant;
        this.nameServant = nameServant;
        this.noblePhantasm = noblePhantasm;
    }

}