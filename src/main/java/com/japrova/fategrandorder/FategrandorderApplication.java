package com.japrova.fategrandorder;

import com.japrova.fategrandorder.dao.UserDao;
import com.japrova.fategrandorder.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class FategrandorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FategrandorderApplication.class, args);
	}
}
