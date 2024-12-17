package com.tomato.running.domain.run;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StartLocation {

    @Column(nullable = false)
    private Float startLongitude;

    @Column(nullable = false)
    private Float startLatitude;
}
