package com.tomato.running.domain.meeting;

import com.tomato.running.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String startedLocation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String endedLocation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
