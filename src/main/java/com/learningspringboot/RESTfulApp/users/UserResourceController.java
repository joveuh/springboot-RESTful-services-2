package com.learningspringboot.RESTfulApp.users;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learningspringboot.RESTfulApp.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResourceController {

    private UsersDaoService service;

    public UserResourceController(UsersDaoService service) {
        this.service = service;
    }

    @GetMapping({ "/users/**" })
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
            // Dev tools will print out an unnecessarilly long trace. Keep in mind, when you
            // build the code, the .jar will not have dev tools enabled so in production the
            // trace won't show. Sweet eh! ?
            throw new UserNotFoundException("User with id " + id + " not found.");
        return user;
    }

    // This HATEOAS will enabe client to see in the returned response an 
    // additional link to the endpoint that lists all users
    @GetMapping("/h1/users/{id}")
    public EntityModel<User> retrieveUserH1(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
            // Dev tools will print out an unnecessarilly long trace. Keep in mind, when you
            // build the code, the .jar will not have dev tools enabled so in production the
            // trace won't show. Sweet eh! ?
            throw new UserNotFoundException("User with id " + id + " not found.");
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder getAllUsersLink = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(getAllUsersLink.withRel("all-users"));
        return entityModel;
    }

    @PostMapping({ "/users/**" })
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User user = service.deleteOne(id);
        if (user == null)
            throw new UserNotFoundException("User with id " + id + " not found.");
        return ResponseEntity.accepted().body(user);
    }

    // If you issue request to http://localhost:8091/users/ it will fail.. it needs
    // to be made to http://localhost:8091/users
    @DeleteMapping({ "/users/**" })
    public ResponseEntity<List<Integer>> deleteAllUsers() {
        List<Integer> deletedUsers = service.deleteAll();
        if (deletedUsers == null || deletedUsers.size() < 1)
            throw new UserNotFoundException("No more users remain.");
        // System.out.print(String.format("\n\n %s \n\n", deletedUsers.toString()));
        return ResponseEntity.accepted().body(deletedUsers);
    }
}
