package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.exceptions.ServantNotFound;
import jakarta.persistence.*;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ErrorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FateGoRepository implements FateGoDao {

    private final EntityManager entityManager;
    @Autowired
    public FateGoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Servant> findServantByName(String name) {

        String hql = "FROM Servant WHERE nameServant = :nameParam";

        TypedQuery<Servant> servantTypedQuery = entityManager
                .createQuery(hql, Servant.class);

        servantTypedQuery.setParameter("nameParam", name);

        Servant servant = null;
        try {
            servant = servantTypedQuery.getSingleResult();

        } catch (NoResultException nr) {
        }
        return Optional.ofNullable(servant);
    }

    @Override
    @Modifying
    public boolean saveServantClass(int idClass, int idServant) {

        Query queryNative = entityManager
                .createNativeQuery("INSERT INTO classes_servants VALUES (:idClass, :idServant)");

        queryNative.setParameter("idClass", idClass);
        queryNative.setParameter("idServant", idServant);

        try {
            return queryNative.executeUpdate() > 1;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    @Modifying
    public boolean saveServanTypes(int idLetter, int idServant) {

        Query queryNative = entityManager
                .createNativeQuery("INSERT INTO servants_types VALUES (:idLetter, :idServant)");

        queryNative.setParameter("idLetter", idLetter);
        queryNative.setParameter("idServant", idServant);

        try {

            return queryNative.executeUpdate() > 1;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }

    }
}
