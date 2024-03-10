package com.japrova.fategrandorder.dao;

import com.japrova.fategrandorder.entity.Servant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDao extends JpaRepository<Servant, Integer> {
}
