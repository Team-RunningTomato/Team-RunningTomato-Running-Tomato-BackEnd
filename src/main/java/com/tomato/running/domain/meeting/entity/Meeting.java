package com.tomato.running.domain.meeting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tomato.running.domain.run.entity.EndLocation;
import com.tomato.running.domain.run.entity.StartLocation;
import com.tomato.running.domain.user.entity.User;
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

    @Embedded
    private StartLocation startLocation;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String addressDetail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void updateMeeting(String title, Integer distance, LocalDateTime startAt,
                              StartLocation startLocation, String addressDetail) {
        this.title = title;
        this.distance = distance;
        this.startAt = startAt;
        this.startLocation = startLocation;
        this.addressDetail = addressDetail;
    }

}
