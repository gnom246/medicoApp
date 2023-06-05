package com.medicoApp.medicoApp.services;

import com.medicoApp.medicoApp.dao.UserRepository;
import com.medicoApp.medicoApp.dtos.ShortUserData;
import com.medicoApp.medicoApp.entities.ROLE;
import com.medicoApp.medicoApp.entities.UserEntity;
import com.medicoApp.medicoApp.exception.UserDoesntExistException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Service
public class UserService implements InitializingBean {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    private boolean logged;
    private Long userId;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        logged = false;

    }

    public void saveUser(String name, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(name);
        userEntity.setUserPassword(passwordEncoder.encode(password));
        userEntity.setRole(ROLE.ROLE_USER);

        userRepository.save(userEntity);

    }

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void destroy() {

    }

    public void userLogin(String userName, String userPassword) {
        UserEntity userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserDoesntExistException(userName));

        if (!passwordEncoder.matches(userPassword, userEntity.getUserPassword())) {
            throw new UserDoesntExistException(userName);
        }
        logged = true;
        userId= userEntity.getId();
    }

    public boolean isLogged() {
        return logged;
    }
    public Long getLoggedUserId() {
        return userId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public Optional<ShortUserData> getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .map(entity -> new ShortUserData(entity.getId(), entity.getUserName(), entity.getRole()));

    }
}
