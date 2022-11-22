package com.example.demolab3.controllers;

import com.example.demolab3.dao.UserDao;
import com.example.demolab3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;
    @GetMapping("/login")
    public String loginPage() {
//zwrócenie nazwy widoku logowania - login.html
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
//dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
//zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute @Valid User user,
                                   BindingResult binding) {
        if (binding.hasErrors()) {
            return "register"; //powrót do rejestracji
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
//przekierowanie do adresu url: /login
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
//dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", dao.findByLogin(principal.getName()));
//zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }

    @GetMapping("/users")
    public String getUsers(Model m, Principal principal) {
//dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("users", dao.findAll());
//zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "users";
    }

    @GetMapping("/edit")
    public String editPage(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
//dodanie do modelu nowego użytkownika
        m.addAttribute("user", user);
//zwrócenie nazwy widoku rejestracji - register.html
        return "edit";
    }

    @PostMapping("/edit")
    public String editAccount(@ModelAttribute @Valid User user, BindingResult binding, Principal principal) {
        if (binding.hasErrors()) {
            return "edit";
        }
        User user2 = dao.findByLogin(principal.getName());
        if(user2!=null) {
            user2.setLogin(user.getLogin());
            user2.setName(user.getName());
            user2.setSurname(user.getSurname());
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.save(user2);
            return "redirect:/edit";
        }
//przekierowanie do adresu url: /login
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteAccount(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        if(user!=null){
            dao.delete(user);
        }
        return "redirect:/logout";
    }

}
