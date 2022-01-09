package kr.ac.hs.selab.common.template;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseTemplate<T> {

    private final String message;
    private final String code;
    private final LocalDateTime serverDateTime;
    private final T data;

    private ResponseTemplate(ResponseMessage message, T data) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseTemplate<T>> of(ResponseMessage message, T data) {
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ResponseTemplate<>(message, data)
            );
    }
}