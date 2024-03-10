package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.ServantDao;
import com.japrova.fategrandorder.dao.ServantRepository;
import com.japrova.fategrandorder.dao.SpringDataDao;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServantServiceImpl implements ServantService {

    private final ServantDao servantDao;
    private final SpringDataDao springDataDao;

    public ServantServiceImpl(ServantDao servantDao, SpringDataDao springDataDao) {
        this.servantDao = servantDao;
        this.springDataDao = springDataDao;
    }

    @Override
    public List<Map<String, String>> findAllServants() {

        try {
            List<Servant> servantList = servantDao.findAllServants();

            // To avoid displaying sensitive data such as ids I had to create a list and store maps.

            return servantList.stream()
                    .map(s -> {
                        Map<String, String> mapServant = new HashMap<>();

                        mapServant.put("nameServant", s.getNameServant());
                        mapServant.put("noblePhantasm", s.getNoblePhantasm());
                        mapServant.put("servantClass", s.getServantClass().getClassName());
                        mapServant.put("letterType", s.getLettersTypes().getLetterType());

                        return mapServant;
                    })
                    .toList();


        } catch (ServantNotFound snf) {
            throw new ServantNotFound("NO SERVANT WAS FOUND IN THE DATABASE");
        }
    }

    @Override
    public Map<String, String> findByName(String name) {

        // We remove the hyphens in the name

        String[] names = name.split("-");

        String nameServant = String.join(" ", names);

        Optional<Servant> optionalServant = servantDao.findServantByName(nameServant);

        if (optionalServant.isPresent()) {

            Servant servant = optionalServant.get();

            Map<String, String> servantMap = new HashMap<>();

            servantMap.put("nameServant", servant.getNameServant());
            servantMap.put("noblePhantasm", servant.getNoblePhantasm());
            servantMap.put("servantClass", servant.getServantClass().getClassName());
            servantMap.put("letterType", servant.getLettersTypes().getLetterType());

            return servantMap;
        }

        throw new ServantNotFound("SERVANT NOT FOUND :: " + nameServant);
    }

    @Override
    public List<Classes> findAllClasses() {

        List<Classes> classesList = servantDao.findAllClasses();

        return classesList;
    }

    @Override
    public List<LettersTypes> findAllLetters() {

        List<LettersTypes> lettersTypes = servantDao.findAllLetters();

        return lettersTypes;
    }

    @Override
    @Transactional
    public boolean persistServant(Servant s) {

        s.setIdServant(0);

        int idClass = s.getServantClass().getIdClass();
        int idLetter = s.getLettersTypes().getIdLetter();

        s.setServantClass(null);
        s.setLettersTypes(null);

        Servant servant = springDataDao.save(s);

        boolean validationClass = servantDao
                .saveServantClass(idClass, servant.getIdServant());

        boolean validationLetter = servantDao
                .saveServanTypes(idLetter, servant.getIdServant());

        return validationClass && validationLetter;

    }

    @Override
    @Transactional
    public boolean updateServant(Servant servant) {
        return false;
    }
}
