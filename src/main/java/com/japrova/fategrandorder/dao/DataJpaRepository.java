package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaRepository extends JpaRepository<Servant, Integer> {
}
