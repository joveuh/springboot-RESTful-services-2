package com.learningspringboot.RESTfulApp.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.learningspringboot.RESTfulApp.exception.UserNotFoundException;

/**
 * DAO stands for Data Access Object
 */

@Component
public class UsersDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Uzair", LocalDate.of(1990, 12, 14)));
        users.add(new User(++usersCount, "Subject", LocalDate.of(1980, 12, 12)));
        users.add(new User(++usersCount, "User", LocalDate.of(1995, 04, 04)));
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

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
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
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User deleteOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id); 
        User user = users.stream().filter(predicate).findFirst().orElse(null);
        users.removeIf(predicate);
        return user;
    }

    public List<Integer> deleteAll() {
        List<Integer> deletedUsers = new ArrayList<>();
        users.forEach(a -> deletedUsers.add(a.getId()));
        users.clear();
        return deletedUsers;
    }
    
}
