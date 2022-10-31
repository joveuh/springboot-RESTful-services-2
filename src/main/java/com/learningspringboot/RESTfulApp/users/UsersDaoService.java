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
        users.add(new User(1, "Uzair", LocalDate.of(1990, 12, 14)));
        users.add(new User(2, "Subject", LocalDate.of(1980, 12, 12)));
        users.add(new User(3, "User", LocalDate.of(1995, 04, 04)));
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
