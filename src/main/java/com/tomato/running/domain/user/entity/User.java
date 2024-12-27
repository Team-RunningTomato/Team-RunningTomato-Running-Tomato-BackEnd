package com.tomato.running.domain.user.entity;

import com.tomato.running.domain.user.presentation.data.req.UpdateWeightAndHeightRequestDto;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, columnDefinition = "VARCHAR(5)")
    private String gender;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String age;

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String mobile;

    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String name;

    @Column(columnDefinition = "VARCHAR(10)")
    private String weight;

    @Column(columnDefinition = "VARCHAR(10)")
    private String height;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void saveWeightAndHeight(UpdateWeightAndHeightRequestDto dto) {
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }
}