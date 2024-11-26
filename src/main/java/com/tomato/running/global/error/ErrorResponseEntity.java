package com.tomato.running.global.error;
import org.springframework.http.ResponseEntity;

public record ErrorResponseEntity(int status, String message) {

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new ErrorResponseEntity(
                        e.getStatus(),
                        e.getMessage()
                ));
    }
}