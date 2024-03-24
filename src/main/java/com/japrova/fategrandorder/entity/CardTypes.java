package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "card_type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CardTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCard;

    @Column(name = "card_type")
    private String cardName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "cardTypes")
    private Set<Servant> servants;

    public CardTypes(int idCard, String cardName) {
        this.idCard = idCard;
        this.cardName = cardName;
    }
}