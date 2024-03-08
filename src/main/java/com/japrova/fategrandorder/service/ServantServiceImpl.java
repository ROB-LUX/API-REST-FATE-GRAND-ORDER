package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.ServantDao;
import com.japrova.fategrandorder.dao.ServantRepository;
import com.japrova.fategrandorder.entity.Classes;
import com.japrova.fategrandorder.entity.LettersTypes;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServantServiceImpl implements ServantService {

    private final ServantDao servantDao;

    private final ServantRepository servantRepository;

    public ServantServiceImpl(ServantDao servantDao, ServantRepository servantRepository) {
        this.servantDao = servantDao;
        this.servantRepository = servantRepository;
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

        Optional<Servant> optionalServant = servantRepository.findServantByName(nameServant);

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
}
