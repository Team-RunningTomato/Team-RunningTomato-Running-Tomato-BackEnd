package com.tomato.running.domain.user.util;

import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.exception.UserNotFoundException;
import com.tomato.running.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
