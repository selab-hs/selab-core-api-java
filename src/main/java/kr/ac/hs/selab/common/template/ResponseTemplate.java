package kr.ac.hs.selab.common.template;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;

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
        @NotNull HttpStatus status) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
        this.status = status;
    }

    // 주로 사용하는 것만 공통으로 빼자..
    // 가끔 쓰는 건 쓰는곳에서 사용하기
    public static <T> ResponseTemplate<T> created(final ResponseMessage message, final T data) {
        return ResponseTemplate.of(message, data, HttpStatus.CREATED);
    }

    public static <T> ResponseTemplate<T> ok(final ResponseMessage message, final T data) {
        return ResponseTemplate.of(message, data, HttpStatus.OK);
    }
}