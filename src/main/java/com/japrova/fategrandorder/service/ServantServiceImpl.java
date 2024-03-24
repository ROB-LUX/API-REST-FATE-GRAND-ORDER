package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.*;
import com.japrova.fategrandorder.dto.CardTypeDto;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.*;
import com.japrova.fategrandorder.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServantServiceImpl implements ServantService {

    private final FateGoDao servantDao;
    private final SpringDataDao springDataDao;

    public ServantServiceImpl(FateGoDao servantDao, SpringDataDao springDataDao) {
        this.servantDao = servantDao;
        this.springDataDao = springDataDao;
    }

    @Override
    public List<ServantDto> findAllServants() {

        try {
            List<Servant> servantList = springDataDao.findAllServants();

            // To avoid displaying sensitive data such as ids I had to create a list and store maps.

            return servantList.stream()
                    .map(s ->
                         new ServantDto(s.getIdServant(), s.getNameServant(),
                                s.getNoblePhantasm(), s.getServantClass().getClassName(), s.getCardTypes().stream()
                                 .map(cardTypes -> new CardTypeDto(cardTypes.getCardName()))
                                 .collect(Collectors.toSet()))

                    )
                    .toList();

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    public ServantDto findByName(String name) {

        // We remove the hyphens in the card

        String[] names = name.split("-");

        String nameServant = String.join(" ", names);

        Optional<Servant> optionalServant = servantDao.findServantByName(nameServant);

        if (optionalServant.isPresent()) {

            Servant servant = optionalServant.get();

            /*return new ServantDto(servant.getIdServant(),
                    servant.getNameServant(), servant.getNoblePhantasm(), servant.getServantClass().getClassName(),
                    servant.getLettersTypes().getLetterType());*/

            return null;
        }

        throw new ServantNotFound("SERVANT NOT FOUND :: " + nameServant);
    }

    @Override
    public List<Classes> findAllClasses() {


        try {
            List<Classes> classesList = springDataDao.findAllClasses();

            return classesList;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    public List<CardTypes> findAllLetters() {

        try {
            List<CardTypes> lettersTypes = springDataDao.findAllLetters();

            return lettersTypes;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ServantDto saveServant(final ServantDto servant) {

        /*int idLetter = servant.getLettersTypes().getIdLetter();*/

        Set<CardTypes> cardTypes = springDataDao.findAllCardTypes();

        Servant servant1 = Servant.builder()
                .idServant(0)
                .nameServant(servant.nameServant())
                .noblePhantasm(servant.noblePhantasm())
                .servantClass(new Classes(3, "Nueva clase"))
                .cardTypes(cardTypes)
                .build();

        springDataDao.save(servant1);

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto updateServant(ServantDto servantDto) {

        if (servantDto != null && servantDto.idServant() == 0) {

            throw new ServantNotFound("Error with the id");
        }

        return findServantDto(null);
    }

    @Override
    public void deleteServant(int idServant) {

        if (idServant == 0) {
            throw new ServantNotFound("Error with ids");
        }

        Optional<Servant> optionalServant = springDataDao.findById(idServant);

        Servant servant = optionalServant.
                orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND WITH ID " + idServant));

        System.out.println(servant);
        springDataDao.delete(servant);
        springDataDao.deleteById(idServant);
    }

    private ServantDto findServantDto(Servant s) {

        try {

            /*Classes classes = new Classes(s.getServantClass().getIdClass(), s.getServantClass().getClassName());
            CardTypes lettersTypes = new CardTypes(s.getLettersTypes().getIdLetter(), s.getLettersTypes().getLetterType());

            Servant servant = Servant.builder()
                    .idServant(s.getIdServant()).nameServant(s.getNameServant())
                    .noblePhantasm(s.getNoblePhantasm()).servantClass(classes)
                    .lettersTypes(lettersTypes).build();


            servant = springDataDao.save(servant);

            return new ServantDto(servant.getIdServant(), servant.getNameServant(), servant.getNoblePhantasm(), servant.getServantClass().getClassName(),
                    servant.getLettersTypes().getLetterType());*/
            return null;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("ERROR SERVER");
        }
    }
}
