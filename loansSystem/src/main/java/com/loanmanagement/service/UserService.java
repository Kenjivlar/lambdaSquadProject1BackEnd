package com.loanmanagement.service;

import com.loanmanagement.model.User;
import com.loanmanagement.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository personRepository) {
        this.userRepository = personRepository;
    }

    public List<User> getAllPeople() {
        return userRepository.findAll();
    }

    public User saveUser(User users) {

        return userRepository.save(users);
    }

}
