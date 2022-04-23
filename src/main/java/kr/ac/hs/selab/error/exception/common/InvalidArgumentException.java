package kr.ac.hs.selab.error.exception.common;

import kr.ac.hs.selab.error.exception.BusinessException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BusinessException {

    public InvalidArgumentException(final ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}