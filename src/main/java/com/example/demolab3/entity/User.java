package com.example.demolab3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;
    @Size(min = 2, max = 15)
    private String name;
    @Size(min = 2, max = 30)
    private String surname;
    @Size(min = 3, max = 15)
    private String login;
    private String password;
    public User() {
    }
    public User(String name, String surname, String login,
                String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
