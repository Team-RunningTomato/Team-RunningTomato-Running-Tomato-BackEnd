package com.tomato.running.domain.meeting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tomato.running.domain.run.EndLocation;
import com.tomato.running.domain.run.StartLocation;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDateTime startAt;

    @Embedded
    private StartLocation startLocation;

    @Embedded
    private EndLocation endLocation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
