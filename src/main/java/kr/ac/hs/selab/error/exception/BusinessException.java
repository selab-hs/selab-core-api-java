package kr.ac.hs.selab.error.exception;

import kr.ac.hs.selab.error.dto.ErrorMessage;
import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.getDetail());
        this.errorMessage = message;
    }
}