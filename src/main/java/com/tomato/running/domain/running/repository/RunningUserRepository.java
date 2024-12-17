package com.tomato.running.domain.running.repository;

import com.tomato.running.domain.running.RunningUser;
import com.tomato.running.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RunningUserRepository extends JpaRepository<RunningUser, Long> {
    Optional<RunningUser> findByUser(User user);
}
