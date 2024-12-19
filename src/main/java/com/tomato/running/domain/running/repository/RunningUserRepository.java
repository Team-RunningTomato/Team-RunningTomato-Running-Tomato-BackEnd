package com.tomato.running.domain.running.repository;

import com.tomato.running.domain.running.entity.RunningUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningUserRepository extends JpaRepository<RunningUser, Long> {
}
