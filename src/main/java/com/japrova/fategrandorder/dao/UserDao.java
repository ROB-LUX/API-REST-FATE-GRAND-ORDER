package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.User;

public interface UserDao {

    User findByUserName(String theUserName);
}