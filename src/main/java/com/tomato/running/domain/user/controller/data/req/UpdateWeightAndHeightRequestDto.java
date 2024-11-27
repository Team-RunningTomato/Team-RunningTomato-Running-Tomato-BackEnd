package com.tomato.running.domain.user.controller.data.req;

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
