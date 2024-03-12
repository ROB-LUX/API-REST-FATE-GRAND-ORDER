package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.DataNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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

        String sql = "FROM Servant WHERE nameServant = :nameParam";

        TypedQuery<Servant> servantTypedQuery = entityManager
                .createQuery(sql, Servant.class);

        servantTypedQuery.setParameter("nameParam", name);

        Servant undecidedServant = null;

        try {
            undecidedServant = servantTypedQuery.getSingleResult();
        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
        }

        return Optional.ofNullable(undecidedServant);
    }

    @Override
    @Modifying
    public boolean saveServantClass(int idClass, int idServant) {

        Query queryNative = entityManager
                .createNativeQuery("INSERT INTO classes_servants VALUES (:idClass, :idServant)");

        queryNative.setParameter("idClass", idClass);
        queryNative.setParameter("idServant", idServant);

        try {
            return queryNative.executeUpdate() > 0;

        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
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

            return queryNative.executeUpdate() > 0;

        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
        }

    }
}
