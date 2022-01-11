package kr.ac.hs.selab.error.template;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorTemplate {

    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 코드")
    private final String code;
    @Schema(description = "서버시간")
    private final LocalDateTime serverDateTime;

    private ErrorTemplate(ErrorMessage message) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorTemplate> of(ErrorMessage message) {
        return ResponseEntity
            .status(message.getStatus())
            .body(new ErrorTemplate(message));
    }
}