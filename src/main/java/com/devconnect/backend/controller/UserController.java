package com.devconnect.backend.controller;

import com.devconnect.backend.model.User;
import com.devconnect.backend.payload.ApiResponse;
import com.devconnect.backend.repository.UserRepository;
import com.devconnect.backend.service.InMemoryDataBase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // dependency injection
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse<>(true, "Users fetched", userRepository.findAll(), 200);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return new ApiResponse<>(false, "User not found", null, 404);
        }

        return new ApiResponse<>(true, "User fetched", user, 200);
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user){
        boolean exists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(exists){
            return new ApiResponse<>(false, "User with this email already exists", null, 400);
        }

        User savedUser = userRepository.save(user);
        return new ApiResponse<>(true, "User created successfully", savedUser, 201);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<User> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return new ApiResponse<>(false, "User not found", null, 404);
        }

        userRepository.delete(user);
        return new ApiResponse<>(true,"User deleted successfully", user, 200);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return new ApiResponse<>(false, "User not found", null, 404);
        }

        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());

        userRepository.save(user);
        return new ApiResponse<>(true, "User updated successfully", user, 200);

    }


    // ------------ Controllers without using any db ------------------------

//    @GetMapping
//    public ApiResponse<List<User>> getUsers(){
//        List<User> users = InMemoryDataBase.getUsers();
//        return new ApiResponse<>(true, "Users fetched successfully", users, 200);
//    }
//
//    @GetMapping("/{id}")
//    public ApiResponse<User> getUserById(@PathVariable int id){
//        return InMemoryDataBase.getUsers()
//                .stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .map(user -> new ApiResponse<>(true, "User found", user, 200))
//                .orElse(new ApiResponse<>(false, "User not found with id " + id, null, 404));
//    }
//
//    @PostMapping
//    public ApiResponse<User> createUser(@RequestBody User newUser){
//        boolean exists = InMemoryDataBase.getUsers()
//                .stream()
//                .anyMatch(u -> u.getId() == newUser.getId() || u.getEmail().equalsIgnoreCase(newUser.getEmail()));
//
//        if(exists){
//            return new ApiResponse<>(false, "User already exists", null, 500);
//        }
//
//        InMemoryDataBase.addUser(newUser);
//        return new ApiResponse<>(true, "User created successfully", newUser, 201);
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> deleteUser(@PathVariable int id){
//        boolean removed = InMemoryDataBase.getUsers().removeIf(u -> u.getId() == id);
//
//        if(removed){
//            return new ApiResponse<>(true, "User deleted successfully", null, 200);
//        } else  {
//            return new ApiResponse<>(false, "User not found with given id", null, 204);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ApiResponse<User> updateUser(@PathVariable int id, @RequestBody User updateduser){
//        return InMemoryDataBase.getUsers()
//                .stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .map(user -> {
//                    user.setName(updateduser.getName());
//                    user.setEmail(updateduser.getEmail());
//                    return new ApiResponse<>(true, "User updated successfully", user, 200);
//                })
//                .orElse(new ApiResponse<>(false, "User not found", null, 400));
//    }
}
