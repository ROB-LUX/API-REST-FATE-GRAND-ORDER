package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.dao.DataJpaRepository;
import com.japrova.fategrandorder.dao.ServantRepository;
import com.japrova.fategrandorder.entity.Servant;
import com.japrova.fategrandorder.exceptions.ServantNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServantServiceImpl implements ServantService {

    private final DataJpaRepository dataJpaRepository;

    private final ServantRepository servantRepository;

    public ServantServiceImpl(DataJpaRepository dataJpaRepository, ServantRepository servantRepository) {
        this.dataJpaRepository = dataJpaRepository;
        this.servantRepository = servantRepository;
    }

    @Override
    public List<Servant> findAll() {

        return dataJpaRepository.findAll();
    }

    @Override
    public Servant findByName(String name) {

        String[] names = name.split("-");

        String nameServant = Arrays.stream(names)
                .map(n -> n + " ")
                .collect(Collectors.joining()).trim();

        Optional<Servant> optionalServant = servantRepository.findByName(nameServant);

        return optionalServant.orElseThrow(() -> new ServantNotFound("SERVANT NOT FOUND :: " + nameServant));
    }
}
