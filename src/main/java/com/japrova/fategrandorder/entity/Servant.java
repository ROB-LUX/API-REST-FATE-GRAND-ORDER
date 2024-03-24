package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "servants")
@Getter @Setter @NoArgsConstructor @Builder @AllArgsConstructor @ToString(exclude = "cardTypes")
public class Servant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idServant;

    @Column(name = "servant")
    private String nameServant;

    @Column(name = "noble_phantasm")
    private String noblePhantasm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class")
    private Classes servantClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "servant_card", joinColumns = @JoinColumn(name = "servant_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Set<CardTypes> cardTypes;
}