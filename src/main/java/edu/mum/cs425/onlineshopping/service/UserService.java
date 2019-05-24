package edu.mum.cs425.onlineshopping.service;

import edu.mum.cs425.onlineshopping.entity.User;

import java.util.Collection;

public interface UserService {
    User findOne(String email);
    Collection<User> findByRole(String role);
    void save(User user);
    void update(User user);
}
