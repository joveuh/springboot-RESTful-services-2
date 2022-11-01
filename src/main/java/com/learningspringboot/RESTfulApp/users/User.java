package com.learningspringboot.RESTfulApp.users;

import java.time.LocalDate;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {

    private Integer Id;

    @Size(min=2, max=50)
    private String name;

    @Past(message = "Birthday should not be in the future")
    private LocalDate birthDate;

    public User(Integer id, String name, LocalDate birthDate) {
        Id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Users [Id=" + Id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }
}
