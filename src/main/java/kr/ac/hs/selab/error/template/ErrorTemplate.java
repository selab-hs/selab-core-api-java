package kr.ac.hs.selab.error.template;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorTemplate implements Serializable {
    @Schema(description = "응답 코드")
    private final String code;

    @Schema(description = "응답 메세지")
    private final String message;

    @Schema(description = "서버시간")
    private final LocalDateTime serverDateTime;

    public ErrorTemplate(@NotNull final ErrorMessage message) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
    }

    public ErrorTemplate(@NotNull final ErrorMessage message, @NotNull final String reason) {
        this.message = message.name();
        this.code = reason;
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorTemplate> of(HttpStatus status, ErrorMessage message) {
        return ResponseEntity
                .status(status)
                .body(new ErrorTemplate(message));
    }

    public static ResponseEntity<ErrorTemplate> of(HttpStatus status, ErrorMessage message, String reason) {
        return ResponseEntity
                .status(status)
                .body(new ErrorTemplate(message, reason));
    }

    public static ResponseEntity<ErrorTemplate> badRequest() {
        return of(HttpStatus.BAD_REQUEST, ErrorMessage.CONFLICT_ERROR);
    }
}