package kr.ac.hs.selab.common.template;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseTemplate<T> {

    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 코드")
    private final String code;
    @Schema(description = "서버시간")
    private final LocalDateTime serverDateTime;
    @Schema(description = "응답 데이터")
    private final T data;

    private ResponseTemplate(ResponseMessage message, T data) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseTemplate<T>> of(ResponseMessage message, T data) {
        return ResponseEntity
            .status(message.getStatus())
            .body(new ResponseTemplate<>(message, data));
    }
}