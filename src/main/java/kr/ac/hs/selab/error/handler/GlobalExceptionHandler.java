package kr.ac.hs.selab.error.handler;

import kr.ac.hs.selab.error.exception.BusinessException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.template.ErrorTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorTemplate> handleBusinessException(
        final BusinessException e) {
        log.error("BusinessException : ", e.getCause());
        ErrorMessage message = e.getErrorMessage();
        return ResponseEntity
            .status(e.getStatus())
            .body(new ErrorTemplate(message));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorTemplate> handleException(final Exception e) {
        log.error("Exception : ", e.getCause());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorTemplate(ErrorMessage.CONFLICT_ERROR));
    }
}