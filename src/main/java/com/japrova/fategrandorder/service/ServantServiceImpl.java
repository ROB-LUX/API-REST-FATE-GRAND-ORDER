package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.FateGoDao;
import com.japrova.fategrandorder.dao.SpringDataDao;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.DataNotFound;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
                    .map(servant -> {

                        ServantDto servantDto = new ServantDto(servant.getNameServant(),
                                servant.getNoblePhantasm(), servant.getServantClass().getClassName()
                                , servant.getLettersTypes().getLetterType());

                        return servantDto;
                    })
                    .toList();


        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
        }
    }

    @Override
    public ServantDto findByName(String name) {

        // We remove the hyphens in the name

        String[] names = name.split("-");

        String nameServant = String.join(" ", names);

        Optional<Servant> optionalServant = servantDao.findServantByName(nameServant);

        if (optionalServant.isPresent()) {

            Servant servant = optionalServant.get();

            ServantDto servantDto = new ServantDto();
            servantDto.setNameServant(servant.getNameServant());
            servantDto.setNoblePhantasm(servant.getNoblePhantasm());
            servantDto.setServantClass(servant.getServantClass().getClassName());
            servantDto.setLetterType(servant.getLettersTypes().getLetterType());

            return servantDto;
        }

        throw new ServantNotFound("SERVANT NOT FOUND :: " + nameServant);
    }

    @Override
    public List<Classes> findAllClasses() {


        try {
            List<Classes> classesList = springDataDao.findAllClasses();

            return classesList;

        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
        }
    }

    @Override
    public List<LettersTypes> findAllLetters() {

        try {
            List<LettersTypes> lettersTypes = springDataDao.findAllLetters();

            return lettersTypes;

        } catch (DataNotFound dnf) {
            throw new DataNotFound("SERVER ERROR");
        }
    }

    @Override
    @Transactional
    public boolean persistServant(Servant s) {

        s.setIdServant(0);

        int idClass = s.getServantClass().getIdClass();
        int idLetter = s.getLettersTypes().getIdLetter();

        s.setServantClass(null);
        s.setLettersTypes(null);

        if (idClass == 0 && idLetter == 0) {
            throw new ServantNotFound("Error with ids");
        }

        Servant servant = springDataDao.save(s);

        boolean validationClass = servantDao
                .saveServantClass(idClass, servant.getIdServant());

        boolean validationLetter = servantDao
                .saveServanTypes(idLetter, servant.getIdServant());

        return validationClass && validationLetter;

    }

    @Override
    @Transactional
    public Servant updateServant(Servant servant) {

        if (servant != null && servant.getIdServant() == 0) {

            throw new ServantNotFound("Error with the id");
        }

        try {
            Servant servantUpdate = springDataDao.save(servant);

            return servantUpdate;
        } catch (DataNotFound dnf) {
            throw new DataNotFound("ERROR SERVER");
        }
    }
}
