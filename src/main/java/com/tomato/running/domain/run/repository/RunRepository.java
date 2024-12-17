package com.tomato.running.domain.run.repository;

import com.tomato.running.domain.run.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RunRepository extends JpaRepository<Run, UUID> {
}
