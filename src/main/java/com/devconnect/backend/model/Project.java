package com.devconnect.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    // Many projects can have many users - many to many
    @ManyToMany
    @JoinTable(
            name =  "project_collaborators",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> collaborators = new ArrayList<>();

    public Project(){}

    public Project(String title, String description, List<User> collaborators) {
        this.title = title;
        this.description = description;
        this.collaborators = collaborators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
    }

    public void addCollaborator(User user){
        collaborators.add(user);
    }

    public List<User> getCollaborators(){
        return collaborators;
    }

    @Override
    public String toString(){
        return "Project{id=" + id + ", title='" + title + "', collaborators=" + collaborators.size() + "}";
    }
}
