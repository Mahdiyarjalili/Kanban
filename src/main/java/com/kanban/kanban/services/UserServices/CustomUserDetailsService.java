package com.kanban.kanban.services.UserServices;

import com.kanban.kanban.model.UserEntity;
import com.kanban.kanban.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Getter
    private UserEntity user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user= userRepository.findUserEntitiesByEmail(username.toLowerCase());
        this.user=user;
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetail(user);
    }
}
