package com.japrova.fategrandorder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idClass;

    @Column(name = "class")
    private String className;

}