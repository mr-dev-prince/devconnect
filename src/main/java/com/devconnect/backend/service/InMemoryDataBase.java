package com.devconnect.backend.service;

import com.devconnect.backend.model.Project;
import com.devconnect.backend.model.User;

import java.util.*;

public class InMemoryDataBase {
    private static final List<User> users = new ArrayList<>();
    private static final List<Project> projects = new ArrayList<>();

    public static void addUser(User user){
        users.add(user);
    }

    public static void addProject(Project project){
        projects.add(project);
    }

    public static List<User> getUsers(){
        return users;
    }

    public static List<Project> getProjects(){
        return projects;
    }
}
