package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServantRepository implements ServantDao {

    private final EntityManager entityManager;
    @Autowired
    public ServantRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Servant> findAllServants() throws ServantNotFound {

        Query queryNative = entityManager
                .createNativeQuery("SELECT * FROM servants");


        return (List<Servant>) queryNative.getResultList();
    }

    public Optional<Servant> findServantByName(String name) {

        String sql = "FROM Servant WHERE nameServant = :nameParam";

        TypedQuery<Servant> servantTypedQuery = entityManager
                .createQuery(sql, Servant.class);

        servantTypedQuery.setParameter("nameParam", name);

        Servant undecidedServant = null;

        try {

            undecidedServant = servantTypedQuery.getSingleResult();

        } catch (NoResultException ignored) {
        }

        return Optional.ofNullable(undecidedServant);
    }

    @Override
    public List<Classes> findAllClasses() {

        Query queryNative = entityManager
                .createNativeQuery("SELECT * FROM classes");


        return (List<Classes>) queryNative.getResultList();
    }

    @Override
    public List<LettersTypes> findAllLetters() {

        Query queryNative = entityManager
                .createNativeQuery("SELECT * FROM letters_types");


        return (List<LettersTypes>) queryNative.getResultList();
    }

    @Override
    @Modifying
    public boolean saveServantClass(int idClass, int idServant) {

        Query queryNative = entityManager
                .createNativeQuery("INSERT INTO classes_servants VALUES (:idClass, :idServant)");

        queryNative.setParameter("idClass", idClass);
        queryNative.setParameter("idServant", idServant);

        return queryNative.executeUpdate() > 0;
    }

    @Override
    @Modifying
    public boolean saveServanTypes(int idLetter, int idServant) {

        Query queryNative = entityManager
                .createNativeQuery("INSERT INTO servants_types VALUES (:idLetter, :idServant)");

        queryNative.setParameter("idLetter", idLetter);
        queryNative.setParameter("idServant", idServant);

        return queryNative.executeUpdate() > 0;
    }
}
