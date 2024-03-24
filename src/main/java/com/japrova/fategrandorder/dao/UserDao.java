package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query(value = "FROM User WHERE userName=:uName AND enabled=true")
    Optional<User> findByUserName(String uName);
}