package com.tomato.running.domain.month;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class StartLocation {

    @Column(nullable = false)
    private Float startLongitude;

    @Column(nullable = false)
    private Float startLatitude;
}
