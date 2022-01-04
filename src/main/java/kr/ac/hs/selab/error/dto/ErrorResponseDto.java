package kr.ac.hs.selab.error.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponseDto {

    private final String code;
    private final LocalDateTime serverDateTime;

    private ErrorResponseDto(ErrorMessage message) {
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorResponseDto> of(ErrorMessage message) {
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ErrorResponseDto(message)
            );
    }
}