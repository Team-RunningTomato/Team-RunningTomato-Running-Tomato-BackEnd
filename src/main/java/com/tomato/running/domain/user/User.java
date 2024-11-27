package com.tomato.running.domain.user;

import com.tomato.running.domain.user.controller.data.req.UpdateWeightAndHeightRequestDto;
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

    private String gender;

    private String birthday;

    private String age;

    private String mobile;

    private String birthyear;

    @Column(length = 10, nullable = false)
    private String name;

    @Column
    private String email;

    private String weight;

    private String height;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void saveWeightAndHeight(UpdateWeightAndHeightRequestDto dto) {
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
    }
}