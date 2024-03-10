package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public interface ServantDao {

    List<Servant> findAllServants() throws ServantNotFound;

    Optional<Servant> findServantByName(String nameServant);

    List<Classes> findAllClasses();

    List<LettersTypes> findAllLetters();

    boolean saveServantClass(int idClass, int idServant);

    boolean saveServanTypes(int idLetter, int idServant);

}
