package com.devconnect.backend.controller;

import com.devconnect.backend.model.Project;
import com.devconnect.backend.model.User;
import com.devconnect.backend.payload.ApiResponse;
import com.devconnect.backend.repository.ProjectRepository;
import com.devconnect.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ApiResponse<List<Project>> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return new ApiResponse<>(true, "Projects fetched", projects, 200);
    }

    @GetMapping("/{id}")
    public ApiResponse<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(p -> new ApiResponse<>(true, "Project fetched", p, 200))
                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
    }

    @PostMapping
    public ApiResponse<Project> addProject(@RequestBody Project newProject) {
        Project saved = projectRepository.save(newProject);
        return new ApiResponse<>(true, "Project added successfully", saved, 201);
    }

    @PutMapping("/{id}")
    public ApiResponse<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectRepository.findById(id)
                .map(p -> {
                    p.setTitle(updatedProject.getTitle());
                    p.setDescription(updatedProject.getDescription());
                    projectRepository.save(p);
                    return new ApiResponse<>(true, "Project updated successfully", p, 200);
                })
                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteProject(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(p -> {
                    projectRepository.delete(p);
                    return new ApiResponse<>(true, "Project deleted successfully", null, 200);
                })
                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
    }

    @PutMapping("/{projectId}/remove-collaborator/{collaboratorId}")
    public ApiResponse<Project> removeCollaborator(@PathVariable Long projectId, @PathVariable Long collaboratorId) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    boolean removed = project.getCollaborators().removeIf(u -> u.getId().equals(collaboratorId));
                    if (!removed) {
                        return new ApiResponse<>(false, "Collaborator not found", project, 404);
                    }
                    projectRepository.save(project);
                    return new ApiResponse<>(true, "Collaborator removed", project, 200);
                })
                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
    }

    @PutMapping("/{projectId}/add-collaborator/{collaboratorId}")
    public ApiResponse<Project> addCollaborator(@PathVariable Long projectId, @PathVariable Long collaboratorId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) return new ApiResponse<>(false, "Project not found", null, 404);

        User user = userRepository.findById(collaboratorId).orElse(null);
        if (user == null) return new ApiResponse<>(false, "User not found", null, 404);

        boolean exists = project.getCollaborators().stream().anyMatch(u -> u.getId().equals(collaboratorId));
        if (exists) return new ApiResponse<>(false, "Collaborator already exists", null, 400);

        project.getCollaborators().add(user);
        projectRepository.save(project);
        return new ApiResponse<>(true, "Collaborator added successfully", project, 200);
    }

//     ---------------- controllers without db and JPA ---------------
//    @GetMapping("/{id}")
//    public ApiResponse<Project> getProjectById(){
//        return InMemoryDataBase.getProjects()
//                .stream()
//                .findFirst()
//                .map(p -> new ApiResponse<>(true, "Project fetched", p, 200))
//                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
//    }
//
//    @GetMapping
//    public ApiResponse<List<Project>> getProjects(){
//        List<Project> projects = InMemoryDataBase.getProjects();
//        return new ApiResponse<>(true, "Projects fetched", projects, 200);
//    }
//
//    @PostMapping
//    public ApiResponse<Project> addProject(@RequestBody Project newProject){
//        boolean exists = InMemoryDataBase.getProjects()
//                .stream()
//                .anyMatch(p -> p.getId() == newProject.getId());
//
//        if(exists){
//            return new ApiResponse<>(false, "Project with the same id already exists", null, 400);
//        }
//
//        InMemoryDataBase.addProject(newProject);
//        return new ApiResponse<>(true, "Project added successfully", newProject, 201);
//    }
//
//    @PutMapping("/{id}")
//    public ApiResponse<Project> updateProject(@PathVariable int id, @RequestBody Project updatedProject){
//        return InMemoryDataBase.getProjects()
//                .stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .map(p -> {
//                    p.setDescription(updatedProject.getDescription());
//                    p.setTitle(updatedProject.getTitle());
//                    return new ApiResponse<>(true, "Project updated successfully", p, 200);
//                })
//                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> deleteProject(@PathVariable int id){
//        boolean removed = InMemoryDataBase.getProjects().removeIf(p -> p.getId() == id);
//
//        if(removed){
//            return new ApiResponse<>(true, "Project deleted successfully", null, 200);
//        }
//
//        return new ApiResponse<>(false, "Project not found", null, 404);
//    }
//
//    @PutMapping("/{projectId}/remove-collaborator/{collaboratorId}")
//    public ApiResponse<Project> removeCollaborator(@PathVariable int projectId, @PathVariable int collaboratorId){
//        return InMemoryDataBase.getProjects()
//                .stream()
//                .filter(p -> p.getId() == projectId)
//                .findFirst()
//                .map(project -> {
//                    boolean removed = project.getCollaborators().removeIf(u -> u.getId() == collaboratorId);
//                    if(removed){
//                        return new ApiResponse<>(true, "Collaborator removed", project, 200);
//                    }else {
//                        return new ApiResponse<>(false, "Collaborator not found", project, 404);
//                    }
//                })
//                .orElse(new ApiResponse<>(false, "Project not found", null, 404));
//
//    }
//
//    @PostMapping("/{projectId}/add-collaborator/{collaboratorId}")
//    public ApiResponse<Project> addCollaborator(@PathVariable int projectId, @PathVariable int collaboratorId){
//        Project project = InMemoryDataBase.getProjects()
//                .stream()
//                .filter(p -> p.getId() == projectId)
//                .findFirst()
//                .orElse(null);
//
//        if(project == null){
//            return new ApiResponse<>(false, "Project not fount", null, 404);
//        }
//
//        var user = InMemoryDataBase.getUsers()
//                .stream()
//                .filter(u -> u.getId() == collaboratorId)
//                .findFirst()
//                .orElse(null);
//
//        if(user == null){
//            return new ApiResponse<>(false, "User not found", null, 404);
//        }
//
//        boolean exists = project.getCollaborators()
//                .stream()
//                .anyMatch(u -> u.getId() == collaboratorId);
//
//        if(exists){
//            return new ApiResponse<>(false, "Collaborator already exists in the project", null, 400);
//        }
//
//        project.getCollaborators().add(user);
//        return new ApiResponse<>(true, "Collaborator added successfully", project, 200);
//    }
}
