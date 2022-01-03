package kr.ac.hs.selab.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseDto<T> {

    private final String code;
    private final String name;
    private final LocalDateTime serverDateTime;
    private final T data;

    private ResponseDto(ResponseMessage message, T data) {
        this.code = message.getCode();
        this.name = message.name();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseDto<T>> of(ResponseMessage message, T data) {
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ResponseDto<>(message, data)
            );
    }
}