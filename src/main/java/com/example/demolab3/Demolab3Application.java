package com.example.demolab3;

import com.example.demolab3.dao.UserDao;
import com.example.demolab3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Demolab3Application {
    @Autowired
    private UserDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Demolab3Application.class, args);
    }

    @PostConstruct
    public void init() {
        dao.save(new User("Piotr", "Piotrowski","admin",
                passwordEncoder.encode("admin123")));
        dao.save(new User("Ania", "Annowska","ania",
                passwordEncoder.encode("ania1234")));
    }

}
