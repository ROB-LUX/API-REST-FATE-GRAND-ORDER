package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.CardTypes;
import com.japrova.fategrandorder.entity.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SpringDataDao extends JpaRepository<Servant, Integer> {

    @Query("FROM CardTypesEnum")
    List<CardTypes> findAllLetters();

    @Query("FROM Classes")
    List<Classes> findAllClasses();

    @Query("FROM CardTypesEnum")
    Set<CardTypes> findAllCardTypes();
}
