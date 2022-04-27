package kr.ac.hs.selab.error.handler;

import kr.ac.hs.selab.error.exception.BusinessException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.template.ErrorTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.CredentialExpiredException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected ResponseEntity<ErrorTemplate> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.error("[ERROR] InternalAuthenticationServiceException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_INTERNAL_AUTHENTICATION_SERVICE_ERROR
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorTemplate> handleBadCredentialsException(BadCredentialsException e) {
        log.error("[ERROR] BadCredentialsException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_BAD_CREDENTIALS_ERROR
        );
    }

    @ExceptionHandler(LockedException.class)
    protected ResponseEntity<ErrorTemplate> handleLockedException(LockedException e) {
        log.error("[ERROR] LockedException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_LOCKED_ERROR
        );
    }

    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<ErrorTemplate> handleDisabledException(DisabledException e) {
        log.error("[ERROR] DisabledException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_DISABLE_ERROR
        );
    }

    @ExceptionHandler(AccountExpiredException.class)
    protected ResponseEntity<ErrorTemplate> handleAccountExpiredException(AccountExpiredException e) {
        log.error("[ERROR] AccountExpiredException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_ACCOUNT_EXPIRED_ERROR
        );
    }

    @ExceptionHandler(CredentialExpiredException.class)
    protected ResponseEntity<ErrorTemplate> handleCredentialExpiredException(CredentialExpiredException e) {
        log.error("[ERROR] CredentialExpiredException -> {}", e.getMessage());

        return ErrorTemplate.of(
                HttpStatus.UNAUTHORIZED,
                ErrorMessage.AUTH_CREDENTIAL_EXPIRED_ERROR
        );
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorTemplate> handleBusinessException(
            final BusinessException e) {
        var message = e.getErrorMessage();
        log.error("[ERROR] BusinessException -> {}", message.getDetail());
        return ErrorTemplate.of(e.getStatus(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorTemplate> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("[ERROR] MethodArgumentNotValidException -> {}", e.getBindingResult());
        return ErrorTemplate.of(
                HttpStatus.BAD_REQUEST,
                ErrorMessage.METHOD_ARGUMENT_NOT_VALID_ERROR,
                e.getBindingResult().toString()
        );
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorTemplate> handleException(final Exception e) {
        log.error("[ERROR] Exception -> {}", e.getCause());
        return ErrorTemplate.badRequest();
    }
}