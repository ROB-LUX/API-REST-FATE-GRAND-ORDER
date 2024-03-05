package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "letters_types")
public class LettersTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_letter")
    private int idLetter;

    @Column(name = "letter_type")
    private String letterType;

    public LettersTypes() {
    }

    public int getIdLetter() {
        return idLetter;
    }

    public void setIdLetter(int idLetter) {
        this.idLetter = idLetter;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }
}