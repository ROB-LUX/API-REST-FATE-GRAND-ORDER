package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.ServantDao;
import com.japrova.fategrandorder.dao.ServantRepository;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServantServiceImpl implements ServantService {

    private final ServantDao servantDao;

    private final ServantRepository servantRepository;

    public ServantServiceImpl(ServantDao servantDao, ServantRepository servantRepository) {
        this.servantDao = servantDao;
        this.servantRepository = servantRepository;
    }

    @Override
    public List<Map<String, String>> findAll() {

        try {
            List<Servant> servantList = servantDao.findAll();

            // To avoid displaying sensitive data such as ids I had to create a list and store maps.

            List<Map<String, String>> servantsMap = servantList.stream()
                    .map(s -> {
                        Map<String, String> mapServant = new HashMap<>();

                        mapServant.put("nameServant", s.getNameServant());
                        mapServant.put("noblePhantasm", s.getNoblePhantasm());
                        mapServant.put("servantClass", s.getServantClass().getClassName());
                        mapServant.put("letterType", s.getLettersTypes().getLetterType());

                        return mapServant;
                    })
                    .toList();

            return servantsMap;

        } catch (ServantNotFound snf) {
            throw new ServantNotFound("NO SERVANT WAS FOUND IN THE DATABASE");
        }
    }

    @Override
    public Servant findByName(String name) {

        String[] names = name.split("-");

        /*String nameServant = Arrays.stream(names)
                .map(n -> n + " ")
                .collect(Collectors.joining()).trim();*/

        String nameServant = String.join(" ", names);

        System.out.println(nameServant);
        Optional<Servant> optionalServant = servantRepository.findByName(nameServant);

        return optionalServant.orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND :: " + nameServant));
    }
}
