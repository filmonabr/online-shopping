package edu.mum.cs425.onlineshopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.enums.ResultEnum;
import edu.mum.cs425.onlineshopping.exception.MyException;
import edu.mum.cs425.onlineshopping.repository.UserRepository;
import edu.mum.cs425.onlineshopping.service.UserService;

import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }
    }

    @Override
    public void update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        userRepository.save(oldUser);
    }

}
