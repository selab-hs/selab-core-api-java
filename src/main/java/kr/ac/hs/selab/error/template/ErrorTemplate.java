package kr.ac.hs.selab.error.template;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorTemplate {

    private final String message;
    private final String code;
    private final LocalDateTime serverDateTime;

    private ErrorTemplate(ErrorMessage message) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorTemplate> of(ErrorMessage message) {
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ErrorTemplate(message)
            );
    }
}