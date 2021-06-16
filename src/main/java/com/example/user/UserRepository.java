package com.example.user;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

    private AtomicInteger idGen = new AtomicInteger();

    private Map<Integer, User> users = new HashMap<>();

    public User getUser(int id) {
        return users.get(id);
    }

    public User addUser(User user) {
        if(user.getId() == 0) {
            user.setId(idGen.incrementAndGet());
        } else if(user.getId() > idGen.get()) {
            idGen.set(user.getId());
        }
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(User user) {
        User perUser = users.get(user.getId());
        if(StringUtils.hasText(user.getName())) {
            perUser.setName(user.getName());
        }
        if(user.getAge() != 0) {
            perUser.setAge(user.getAge());
        }
        return perUser;
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteAll() {
        users.clear();
    }
}
