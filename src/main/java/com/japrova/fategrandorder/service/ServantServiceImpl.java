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

            return new ServantDto(servant.getIdServant(),
                    servant.getNameServant(), servant.getNoblePhantasm(), servant.getServantClass().getClassName(),
                    servant.getLettersTypes().getLetterType());

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto saveServant(final ServantDto servantDto) {

        int idClass = Integer.parseInt(servantDto.servantClass());
        int idLetter = Integer.parseInt(servantDto.letterType());

        if (idClass == 0 && idLetter == 0) {
            throw new ServantNotFound("Error with ids");
        }

        Servant servant = new Servant(servantDto.idServant(), servantDto.nameServant(), servantDto.noblePhantasm());

        try {
            Servant SavSer = springDataDao.save(servant); // SavSer = save Servant

            int idServant = SavSer.getIdServant();

            servantDao.saveServantClass(idClass, idServant);
            servantDao.saveServanTypes(idLetter, idServant);

            int idClasses = servantDao.findServantClass(idServant);
            int idLetters = servantDao.findServantType(idServant);


            return new ServantDto(idServant, SavSer.getNameServant(), SavSer.getNoblePhantasm(),
                    String.valueOf(idClasses), String.valueOf(idLetters));

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("ROLLBACK ACTIVATE");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServantDto updateServant(ServantDto servantDto) {

        if (servantDto != null && servantDto.idServant() == 0) {

            throw new ServantNotFound("Error with the id");
        }

        try {

            Servant servant = new Servant(servantDto.idServant(),
                    servantDto.nameServant(), servantDto.noblePhantasm());

            servant = springDataDao.save(servant);

            int servantId = servant.getIdServant();

            servantDao.saveServantClass(Integer.parseInt(servantDto.servantClass()), servantId);
            servantDao.saveServanTypes(Integer.parseInt(servantDto.letterType()), servantId);

            int idClasses = servantDao.findServantClass(servantId);

            int idLetters = servantDao.findServantType(servantId);

            return new ServantDto(servantId, servant.getNameServant(), servant.getNoblePhantasm(), String.valueOf(idClasses),
                    String.valueOf(idLetters));

        } catch (ErrorPersistence dnf) {
            throw new ErrorPersistence("ERROR SERVER");
        }
    }

    @Override
    public void deleteServant(int idServant) {

        if (idServant == 0) {
            throw new ServantNotFound("Error with ids");
        }

        Optional<Servant> optionalServant = springDataDao.findById(idServant);

        Servant servant = optionalServant.
                orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND WITH ID " + idServant));

        springDataDao.delete(servant);
    }
}
