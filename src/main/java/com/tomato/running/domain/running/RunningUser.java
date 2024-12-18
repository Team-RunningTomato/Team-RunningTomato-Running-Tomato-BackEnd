package com.tomato.running.domain.running;

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
public class RunningUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, columnDefinition = "BIGINT(10)")
    private Long totalDistance;

    @Column(nullable = false, columnDefinition = "BIGINT(10)")
    private Long bestDistance;

    @Column(nullable = false, columnDefinition = "BIGINT(10)")
    private Long worstDistance;

    @Column(nullable = false)
    private Integer levelPercentage;
}
