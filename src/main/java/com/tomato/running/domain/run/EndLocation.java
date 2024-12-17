package com.tomato.running.domain.run;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class EndLocation {

    @Column(nullable = false)
    private Float endLongitude;

    @Column(nullable = false)
    private Float endLatitude;
}
