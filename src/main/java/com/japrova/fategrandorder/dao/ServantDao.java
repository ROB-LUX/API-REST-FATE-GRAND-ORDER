package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;

import java.util.List;
import java.util.Optional;

public interface ServantDao {

    List<Servant> findAll() throws ServantNotFound;
    Optional<Servant> findByName(String nameServant);
}
