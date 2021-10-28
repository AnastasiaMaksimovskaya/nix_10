package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DBUser {

    private User[] users;
    private User[] newUsers;

    private static DBUser instance;

    private DBUser() {
        users = new User[1];
    }

    public static DBUser getInstance() {
        if (instance == null) {
            instance = new DBUser();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return;
            }
        }
        changeSize();
        create(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        if (current != null) {
            current.setName(user.getName());
            current.setAge(user.getAge());
        } else {
            System.out.println("there is no user with id");
            return;
        }
    }

    public void delete(String id) {
        int position = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId().equals(id)) {
                    users[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < users.length - 1; i++) {
                users[i] = users[i + 1];
            }
            users[users.length - 1] = null;
        } else System.out.println("there is no user with id = " + id);
    }

    public User findById(String id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getId().equals(id)) {
                    return users[i];
                }
            }
        }
        return null;
    }

    public User[] findAll() {
        int counter = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                counter++;
            }
        }
        User newUsers[] = Arrays.copyOf(users, counter);
        return newUsers;
    }

    public boolean existByEmail(String email) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                return false;
            }
            if (users[i].getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (findById(id) == null) {
            return id;
        }
        return generateId();
    }

    public void changeSize() {
        User newUsers[] = new User[users.length * 2];
        for (int i = 0; i < users.length; i++) {
            newUsers[i] = users[i];
        }
        users = newUsers;
    }

}
