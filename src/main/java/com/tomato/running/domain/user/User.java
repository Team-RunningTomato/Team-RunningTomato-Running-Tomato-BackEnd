package com.tomato.running.domain.user;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 10, nullable = false)
    private Float height;

    @Column(length = 10, nullable = false)
    private Float weight;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String area;
}
