package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.exceptions.ServantNotFound;
import jakarta.persistence.*;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ErrorPersistence;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FateGoRepository implements FateGoDao {

    private final EntityManager entityManager;

    public FateGoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Servant> findServantByName(String name) {

        final String HQL = "FROM Servant WHERE nameServant = :nameParam";

        TypedQuery<Servant> servantTypedQuery = entityManager
                .createQuery(HQL, Servant.class);

        servantTypedQuery.setParameter("nameParam", name);

        Servant servant = null;
        try {
            servant = servantTypedQuery.getSingleResult();

        } catch (NoResultException ignored) {
        }
        return Optional.ofNullable(servant);
    }

    @Override
    public void deleteServant(int idServant) {

        Servant servant = entityManager.find(Servant.class, idServant);


        if (servant != null) {
            Query queryNative = entityManager.createNativeQuery("DELETE FROM servants WHERE id=:theId");

            try {
                queryNative.setParameter("theId", idServant);
                queryNative.executeUpdate();

            } catch (Exception ex) {
                System.out.println("No se pudo controlar");
            }
        } else {
            throw new ServantNotFound("SERVANT NOT FOUND");
        }
    }
}