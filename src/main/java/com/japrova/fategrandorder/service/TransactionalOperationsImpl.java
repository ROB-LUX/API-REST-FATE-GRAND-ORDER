package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.*;
import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.*;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto saveServant(final ServantDto servantDto) {

        final String idCards = servantDto.cardTypeDto().toString();

        Map<String, Object> objectMap = findCardAndClass(Integer.parseInt(servantDto.servantClass()), idCards);

        ClassesEnum classesEnum = (ClassesEnum) objectMap.get("classes");
        Set<CardTypes> cardTypes = (Set<CardTypes>) objectMap.get("cardsSet");

        Servant servant = Servant.builder()
                .nameServant(servantDto.nameServant())
                .noblePhantasm(servantDto.noblePhantasm())
                .servantClass(new Classes(classesEnum.getId(), classesEnum.getClassName()))
                .cardTypes(cardTypes)
                .build();

        servant = springDataDao.save(servant);

        return new ServantDto(servant.getIdServant(), servant.getNameServant(), servant.getNoblePhantasm(),
                classesEnum.getClassName(), servant.getCardTypes().stream()
                .map(c -> new CardTypeDto(c.getIdCard(), c.getCardName())).toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto updateServant(ServantDto servantDto) {

        int idServant = servantDto.idServant();

        if (idServant == 0) {

            throw new ServantNotFound("Error with the id");
        }
        Servant existingServant = springDataDao.findById(idServant)
                .orElseThrow(() -> new ServantNotFound("NO SERVANT FOUND WITH ID: " + idServant));

        final String idCards = servantDto.cardTypeDto().toString();

        Map<String, Object> objectMap = findCardAndClass(Integer.parseInt(servantDto.servantClass()), idCards);

        ClassesEnum classesEnum = (ClassesEnum) objectMap.get("classes");
        Set<CardTypes> cardTypes = (Set<CardTypes>) objectMap.get("cardsSet");

        existingServant.setIdServant(idServant);
        existingServant.setNameServant(servantDto.nameServant());
        existingServant.setNoblePhantasm(servantDto.noblePhantasm());
        existingServant.setServantClass(new Classes(classesEnum.getId(), classesEnum.getClassName()));
        existingServant.setCardTypes(cardTypes);

        existingServant = springDataDao.save(existingServant);


        return new ServantDto(existingServant.getIdServant(), existingServant.getNameServant(), existingServant.getNoblePhantasm(),
                existingServant.getServantClass().getNameClass(), existingServant.getCardTypes().stream()
                .map(c -> new CardTypeDto(c.getIdCard(), c.getCardName())).toList());
    }

    @Override
    @Transactional
    public void deleteServant(int idServant) {

        if (idServant == 0) {
            throw new ServantNotFound("Error with the id");
        }

        springDataDao.findById(idServant)
                .orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND WITH ID " + idServant));

        servantDao.deleteServant(idServant);
    }

    private Map<String, Object> findCardAndClass(int idClass, String cardTypes) {

        Set<CardTypes> cardSet = springDataDao.findAllCardTypes().stream()
                .filter(c -> {
                    String card = String.valueOf(c.getIdCard());
                    return cardTypes.contains(card);
                })
                .collect(Collectors.toSet());

        ClassesEnum classesEnum = Arrays.stream(ClassesEnum.values())
                .filter(classes -> classes.getId() == idClass)
                .findFirst().orElseThrow(() -> new ErrorPersistence("WRONG CLASS"));

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("cardsSet", cardSet);
        objectMap.put("classes", classesEnum);

        return objectMap;
    }
}
