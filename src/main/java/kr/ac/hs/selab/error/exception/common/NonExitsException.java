package kr.ac.hs.selab.error.exception.common;

import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NonExitsException extends BusinessException {

    public NonExitsException(final ErrorMessage message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}