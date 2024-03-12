package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataDao extends JpaRepository<Servant, Integer> {

    @Query("FROM LettersTypes")
    List<LettersTypes> findAllLetters();

    @Query("FROM Classes")
    List<Classes> findAllClasses();

    @Query("FROM Servant")
    List<Servant> findAllServants();
}
