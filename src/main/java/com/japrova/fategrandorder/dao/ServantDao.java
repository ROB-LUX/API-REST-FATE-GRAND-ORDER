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

    default List<?> findRandomData(String sql, EntityManager entityManager
            , Object object) {

        TypedQuery<?> objectTypedQuery = null;

            if (object instanceof Servant) {

                objectTypedQuery = entityManager.createQuery(sql, Servant.class);
            } else if (object instanceof Classes) {

                objectTypedQuery = entityManager.createQuery(sql, Classes.class);
            } else {
                objectTypedQuery = entityManager.createQuery(sql, LettersTypes.class);
            }

        return objectTypedQuery.getResultList();
    }
}
