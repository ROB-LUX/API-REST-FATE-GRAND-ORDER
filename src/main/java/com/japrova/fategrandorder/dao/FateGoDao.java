package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;

import java.util.Optional;

public interface FateGoDao {

    Optional<Servant> findServantByName(String nameServant);

    void deleteServant(int idServant);

}
