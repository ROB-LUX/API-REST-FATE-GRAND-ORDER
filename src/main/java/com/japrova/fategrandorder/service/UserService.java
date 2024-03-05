package com.japrova.fategrandorder.service;

import com.japrova.fategrandorder.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
}
