package kr.ac.hs.selab.common.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ResponseTemplate<T> implements Serializable {
    @JsonIgnore
    private final transient HttpStatus status;
    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 코드")
    private final String code;
    @Schema(description = "서버시간")
    private final LocalDateTime serverDateTime;
    @Schema(description = "응답 데이터")
    private final T data;

    private ResponseTemplate(@NotNull final ResponseMessage message, @NotNull final T data,
                             @NotNull final HttpStatus status) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
        this.status = status;
    }

    public static <T> ResponseTemplate<T> of(final ResponseMessage message, final T data, final HttpStatus status) {
        return new ResponseTemplate<>(message, data, status);
    }

    public static <T> ResponseTemplate<T> ok(final ResponseMessage message, final T data) {
        return of(message, data, HttpStatus.OK);
    }

    public static <T> ResponseTemplate<T> created(final ResponseMessage message, final T data) {
        return of(message, data, HttpStatus.CREATED);
    }
}