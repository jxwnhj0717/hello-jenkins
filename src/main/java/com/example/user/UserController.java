package com.example.user;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Getter
    private UserRepository repository = new UserRepository();

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repository.addUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return repository.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        repository.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return repository.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return repository.getUsers();
    }

}
