package kr.ac.hs.selab.error.exception.common;

import kr.ac.hs.selab.error.dto.ErrorMessage;
import kr.ac.hs.selab.error.exception.BusinessException;

public class InvalidArgumentException extends BusinessException {

    public InvalidArgumentException(ErrorMessage message) {
        super(message);
    }
}