package com.example.demolab3.dao;

import com.example.demolab3.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    public User findByLogin(String login);
}
