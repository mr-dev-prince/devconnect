package com.devconnect.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    public User(){}

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
