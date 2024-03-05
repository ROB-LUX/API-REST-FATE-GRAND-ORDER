package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ServantRepository {

    private EntityManager entityManager;

    @Autowired
    public ServantRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ServantRepository() {
    }

    @Bean
    public ServantRepository getServantRepository() {
        return new ServantRepository();
    }

    public Optional<Servant> findByName(String name) {

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
}
