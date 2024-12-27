package com.tomato.running.domain.user.presentation.data.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateWeightAndHeightRequestDto {

    @NotNull
    private String weight;

    @NotNull
    private String height;
}
