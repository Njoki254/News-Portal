package DAO;


import models.User;

import java.util.List;

public interface UserDao {
    void add(User user);// putting add method mandatory for all hero objects
    User findById(int id);
    List<User> getAllUsers();
    void updateUser(int id, String position, String role, String department);
    void deleteUserById(int id);
    void deleteAllUsers();
}