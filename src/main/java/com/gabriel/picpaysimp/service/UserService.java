package com.gabriel.picpaysimp.service;

import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.exception.UserNotFoundException;
import com.gabriel.picpaysimp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID não encontrado"));
    }
}
