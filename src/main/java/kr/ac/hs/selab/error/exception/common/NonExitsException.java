package kr.ac.hs.selab.error.exception.common;

import kr.ac.hs.selab.error.dto.ErrorMessage;
import kr.ac.hs.selab.error.exception.BusinessException;

public class NonExitsException extends BusinessException {

    public NonExitsException(ErrorMessage message) {
        super(message);
    }
}