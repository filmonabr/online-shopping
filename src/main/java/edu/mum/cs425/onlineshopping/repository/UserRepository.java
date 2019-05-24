package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs425.onlineshopping.entity.User;

import java.util.Collection;


public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);

}
