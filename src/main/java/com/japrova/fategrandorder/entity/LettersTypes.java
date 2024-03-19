package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "letters_types")
@Getter @Setter @NoArgsConstructor
public class LettersTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_letter")
    private int idLetter;

    @Column(name = "letter_type")
    private String letterType;

}