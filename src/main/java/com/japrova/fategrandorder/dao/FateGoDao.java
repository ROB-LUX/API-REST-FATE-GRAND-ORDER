package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;

import java.util.Optional;

public interface FateGoDao {

    Optional<Servant> findServantByName(String nameServant);

    boolean saveServantClass(int idClass, int idServant);

    boolean saveServanTypes(int idLetter, int idServant);

}
