package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.FateGoDao;
import com.japrova.fategrandorder.dao.SpringDataDao;
import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ClassDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.entity.enums.CardTypeEnum;
import com.japrova.fategrandorder.entity.enums.ClassesEnum;
import com.japrova.fategrandorder.exceptions.ErrorPersistence;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObtainingDataImpl implements IObtainingData {

    private final SpringDataDao springDataDao;
    private final FateGoDao repositoryFgo;

    public ObtainingDataImpl(SpringDataDao springDataDao, FateGoDao repositoryFgo) {
        this.springDataDao = springDataDao;
        this.repositoryFgo = repositoryFgo;
    }

    @Override
    public List<ServantDto> findAllServants() {

        try {
            List<Servant> servantList = springDataDao.findAll();

            // To avoid displaying sensitive data such as ids I had to create a list and store maps.

            return servantList.stream()
                    .map(s ->
                            new ServantDto(s.getIdServant(),
                                    s.getNameServant(),
                                    s.getNoblePhantasm(),
                                    s.getServantClass().getNameClass(),
                                    s.getCardTypes().stream()
                                            .map(c -> new CardTypeDto(c.getIdCard(), c.getCardName())).toList()
                            )).toList();

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    public ServantDto findServantByName(String name) {

        // We remove the hyphens in the card

        String[] names = name.split("-");

        String nameServant = String.join(" ", names);

        Optional<Servant> optionalServant = repositoryFgo.findServantByName(nameServant);

        if (optionalServant.isPresent()) {

            Servant servant = optionalServant.get();

            return new ServantDto(servant.getIdServant(),
                    servant.getNameServant(), servant.getNoblePhantasm(), servant.getServantClass().getNameClass(),
                    servant.getCardTypes().stream()
                            .map(card -> new CardTypeDto(card.getIdCard(), card.getCardName())).toList());

        }

        throw new ServantNotFound("SERVANT NOT FOUND :: " + nameServant);
    }

    @Override
    public Set<ClassDto> findClasses() {
        return Arrays.stream(ClassesEnum.values())
                .map(classes -> new ClassDto(classes.getId(), classes.getClassName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CardTypeDto> findCardsType() {
        return Arrays.stream(CardTypeEnum.values())
                .map(card -> new CardTypeDto(card.getId(), card.getCard()))
                .collect(Collectors.toSet());
    }
}
