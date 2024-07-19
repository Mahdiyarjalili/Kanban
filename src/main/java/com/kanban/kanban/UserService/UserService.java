package com.kanban.kanban.UserService;

import com.kanban.kanban.dto.UserDto;
import com.kanban.kanban.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserEntity saveUser (UserDto userdto);
    UserEntity findUserByEmail(String email);
}

