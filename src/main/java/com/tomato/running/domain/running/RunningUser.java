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
    private Integer totalDistance;

    @Column(nullable = false, columnDefinition = "BIGINT(10)")
    private Integer bestDistance;

    @Column(columnDefinition = "BIGINT(10)")
    private Integer worstDistance;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Integer level;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Integer levelPercentage;
}
