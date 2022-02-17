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
        var message = e.getErrorMessage();
        log.error("[ERROR] Exception -> {}", message.getDetail());
        return ErrorTemplate.of(e.getStatus(), message);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorTemplate> handleException(final Exception e) {
        log.error("[ERROR] Exception -> {}", e.getCause());
        return ErrorTemplate.badRequest();
    }
}