package com.tomato.running.domain.run.entity;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
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

    @Column(columnDefinition = "TIME", nullable = false)
    private Time runningTime;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}