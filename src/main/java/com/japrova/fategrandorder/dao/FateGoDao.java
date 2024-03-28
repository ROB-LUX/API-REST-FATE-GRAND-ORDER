package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;

import java.util.Optional;

public interface FateGoDao {

    Optional<Servant> findServantByName(String nameServant);

    int findServantType(int id);
    int findServantClass(int idServant);

    void deleteServant(int idServant);

}
