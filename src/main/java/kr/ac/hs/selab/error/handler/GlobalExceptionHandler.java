package kr.ac.hs.selab.error.handler;

import kr.ac.hs.selab.error.exception.BusinessException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.template.ErrorTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorTemplate> handleBusinessException(
        BusinessException exception) {
        ErrorMessage message = exception.getErrorMessage();
        return ErrorTemplate.of(message);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorTemplate> handleException() {
        return ErrorTemplate.of(ErrorMessage.CONFLICT_ERROR);
    }
}