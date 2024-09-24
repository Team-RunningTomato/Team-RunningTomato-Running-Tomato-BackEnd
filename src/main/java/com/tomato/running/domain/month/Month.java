package com.tomato.running.domain.month;

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
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private Integer distance;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String startedLocation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String endedLocation;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String runningTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
