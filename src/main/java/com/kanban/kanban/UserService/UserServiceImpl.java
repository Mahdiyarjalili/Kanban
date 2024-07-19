package com.kanban.kanban.UserService;

import com.kanban.kanban.dto.UserDto;
import com.kanban.kanban.model.UserEntity;
import com.kanban.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserEntity saveUser(UserDto userdto) {

        UserEntity user = new UserEntity(userdto.getFirstname(),
                userdto.getLastname(), userdto.getEmail().toLowerCase(),
                passwordEncoder.encode(userdto.getPassword()),
                userdto.getRole(), String.valueOf(new Date()));
        if (userRepository.findUserEntitiesByEmail(userdto.getEmail()) != null) {
            return user = null;
        } else {
            return userRepository.save(user);

        }

    }

    @Override
    public UserEntity findUserByEmail(String email) {

        return userRepository.findUserEntitiesByEmail(email.toLowerCase());
    }

}
