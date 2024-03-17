package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.FateGoDao;
import com.japrova.fategrandorder.dao.SpringDataDao;
import com.japrova.fategrandorder.dto.ServantDto;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ErrorPersistence;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
                    .map(servant -> new ServantDto(servant.getIdServant(), servant.getNameServant(),
                            servant.getNoblePhantasm(), servant.getServantClass().getClassName()
                            , servant.getLettersTypes().getLetterType()))
                    .toList();


        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
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
            servantDto.setIdServant(servant.getIdServant());
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

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    public List<LettersTypes> findAllLetters() {

        try {
            List<LettersTypes> lettersTypes = springDataDao.findAllLetters();

            return lettersTypes;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("SERVER ERROR");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ServantDto saveServant(final ServantDto servantDto) {

        servantDto.setIdServant(0);

        int idClass = Integer.parseInt(servantDto.getServantClass());
        int idLetter = Integer.parseInt(servantDto.getLetterType());

        if (idClass == 0 && idLetter == 0) {
            throw new ServantNotFound("Error with ids");
        }

        Servant servant = new Servant(servantDto.getIdServant(), servantDto.getNameServant(), servantDto.getNoblePhantasm());

        try {
            Servant saveServant = springDataDao.save(servant);

            int idServant = saveServant.getIdServant();

            servantDao.saveServantClass(idClass, idServant);

            servantDao.saveServanTypes(idLetter, idServant);

            servantDto.setIdServant(idServant);

            return servantDto;

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("ROLLBACK ACTIVATE");
        }
    }

    @Override
    @Transactional
    public Servant updateServant(final ServantDto servantDto) {

        if (servantDto != null && servantDto.getIdServant() == 0) {

            throw new ServantNotFound("Error with the id");
        }

        try {

            Servant servant = new Servant(servantDto.getIdServant(),
                    servantDto.getNameServant(), servantDto.getNoblePhantasm());

            return springDataDao.save(servant);

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("ERROR SERVER");
        }
    }
}
