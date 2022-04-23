package kr.ac.hs.selab.error.exception.common;

import kr.ac.hs.selab.error.exception.BusinessException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import org.springframework.http.HttpStatus;

public class DuplicationException extends BusinessException {

    public DuplicationException(final ErrorMessage message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}