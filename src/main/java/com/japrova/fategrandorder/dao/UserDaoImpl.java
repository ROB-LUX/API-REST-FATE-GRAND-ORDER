package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;


    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String theUserName) {

        // retrieve/read from database using username
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE userName=:uName AND enabled=true", User.class);
        theQuery.setParameter("uName", theUserName);

        User theUser = null;

        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }
}
