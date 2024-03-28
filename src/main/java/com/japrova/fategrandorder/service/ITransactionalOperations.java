package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.CardTypes;

import java.util.List;

public interface ITransactionalOperations {


    ServantDto saveServant(ServantDto servant);

    ServantDto updateServant(ServantDto servant);

    void deleteServant(int idServant);

}