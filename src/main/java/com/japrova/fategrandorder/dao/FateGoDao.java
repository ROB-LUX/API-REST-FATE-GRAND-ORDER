package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;

import java.util.List;
import java.util.Optional;

public interface FateGoDao {

    Optional<Servant> findServantByName(String nameServant) throws ServantNotFound;

    boolean saveServantClass(int idClass, int idServant);

    boolean saveServanTypes(int idLetter, int idServant);

}
