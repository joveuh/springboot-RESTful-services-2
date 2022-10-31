package com.learningspringboot.RESTfulApp.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

/**
 * DAO stands for Data Access Object
 */

@Component
public class UsersDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Uzair", LocalDate.of(14, 12, 1990)));
        users.add(new User(2, "Subject", LocalDate.of(14, 10, 1980)));
        users.add(new User(3, "User", LocalDate.of(11, 05, 1995)));
    }

    public List<User> findAll() {
        return users;
    }

    public String addUser(User user){
        try{
            users.add(user);
            return "User added successfully";
        } catch (IllegalArgumentException e){
            return "User could not be added";
            // throw e;
        }
    }

    public User findUser(int id){
        Iterator<User> it = users.iterator();
        while (it.hasNext()){
            User curr = it.next();
            if (curr.getId() == id) return curr;
        }
        return null;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id); 
        return users.stream().filter(predicate).findFirst().get();
    }
    
}
