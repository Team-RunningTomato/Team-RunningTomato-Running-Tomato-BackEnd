package com.tomato.running.domain.run;

import com.tomato.running.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Integer distance;

    @Embedded
    private StartLocation startLocation;

    @Embedded
    private EndLocation endLocation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String runningTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}