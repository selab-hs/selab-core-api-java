package kr.ac.hs.selab.error.template;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ErrorTemplate implements Serializable {

    @Schema(description = "응답 메세지")
    private final String message;
    @Schema(description = "응답 코드")
    private final String code;
    @Schema(description = "서버시간")
    private final LocalDateTime serverDateTime;

    public ErrorTemplate(@NotNull final ErrorMessage message) {
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
    }
}