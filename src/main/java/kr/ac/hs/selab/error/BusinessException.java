package kr.ac.hs.selab.error;

import kr.ac.hs.selab.error.dto.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }
}