package com.tomato.running.global.error;
import org.springframework.http.ResponseEntity;

public record ErrorResponseEntity(int status, String name, String message) {

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponseEntity(
                        e.getHttpStatus().value(),
                        e.name(),
                        e.getMessage()
                ));
    }
}