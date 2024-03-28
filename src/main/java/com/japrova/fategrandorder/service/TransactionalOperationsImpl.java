package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.*;
import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.*;
import com.japrova.fategrandorder.entity.enums.CardTypesEnum;
import com.japrova.fategrandorder.entity.enums.ClassesEnum;
import com.japrova.fategrandorder.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TransactionalOperationsImpl implements ITransactionalOperations {

    private final FateGoDao servantDao;
    private final SpringDataDao springDataDao;

    public TransactionalOperationsImpl(FateGoDao servantDao, SpringDataDao springDataDao) {
        this.servantDao = servantDao;
        this.springDataDao = springDataDao;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ServantDto saveServant(final ServantDto servantDto) {

        final String idCards = servantDto.cardTypeDto().toString();

        Set<CardTypes> cardTypes = springDataDao.findAllCardTypes().stream()
                .filter(c -> {
                    String card = String.valueOf(c.getIdCard());


                    return idCards.contains(card);
                })
                .collect(Collectors.toSet());

        Servant servant = Servant.builder()
                .nameServant(servantDto.nameServant())
                .noblePhantasm(servantDto.noblePhantasm())
                .servantClass(new Classes(Integer.parseInt(servantDto.servantClass()), servantDto.servantClass()))
                .cardTypes(cardTypes)
                .build();

        servant = springDataDao.save(servant);

        System.out.println(servant.getServantClass().getIdClass() + " " + servant.getServantClass().getNameClass());
        ServantDto servDto = new ServantDto(servant.getIdServant(), servant.getNameServant(), servant.getNoblePhantasm(),
                servant.getServantClass().getNameClass(), servant.getCardTypes().stream()
                .map(c -> new CardTypeDto(c.getCardName())).toList());

        return servDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto updateServant(ServantDto servantDto) {

        int idServant = servantDto.idServant();

        if (idServant == 0) {

            throw new ServantNotFound("Error with the id");
        }
        Servant existingServant = springDataDao.findById(idServant)
                .orElseThrow(() -> new ServantNotFound("No se encontró ningún Servant con el ID proporcionado: " + idServant));

        Optional<ClassesEnum> classesEnum = Arrays.stream(ClassesEnum.values())
                .filter(classes -> classes.getId() == Integer.parseInt(servantDto.servantClass()))
                .findFirst();

        if (classesEnum.isPresent()) {
            ClassesEnum c = classesEnum.get();

            existingServant.setNameServant(servantDto.nameServant());
            existingServant.setNoblePhantasm(servantDto.noblePhantasm());
            existingServant.setServantClass(new Classes(c.getId(), c.getClassName()));
        }

        String content = servantDto.cardTypeDto().toString();

        Set<CardTypes> cardTypes = springDataDao.findAllCardTypes().stream()
                .filter(c -> {
                    String card = String.valueOf(c.getIdCard());


                    return content.contains(card);
                })
                .collect(Collectors.toSet());

        existingServant.setCardTypes(cardTypes);

        existingServant = springDataDao.save(existingServant);


        ServantDto servDto = new ServantDto(existingServant.getIdServant(), existingServant.getNameServant(), existingServant.getNoblePhantasm(),
                existingServant.getServantClass().getNameClass(), existingServant.getCardTypes().stream()
                .map(c -> new CardTypeDto(c.getCardName())).toList());

        return servDto;
    }

    @Override
    @Transactional
    public void deleteServant(int idServant) {

        if (idServant == 0) {
            throw new ServantNotFound("Error with the id");
        }

        servantDao.deleteServant(idServant);

        /*Optional<Servant> optionalServant = springDataDao.findById(idServant);

        Servant servant = optionalServant.
                orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND WITH ID " + idServant));*/


    }
}
