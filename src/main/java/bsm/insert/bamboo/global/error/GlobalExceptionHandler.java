package bsm.insert.bamboo.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException e) {
        logError(ErrorCode.METHOD_NOT_ALLOWED);
        return createErrorResponseEntity(ErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldError() != null
                ? e.getFieldError().getDefaultMessage()
                : ErrorCode.INVALID_INPUT_VALUE.message;
        logError(errorMessage);
        return createErrorResponseEntity(ErrorCode.INVALID_INPUT_VALUE, errorMessage);
    }

    @ExceptionHandler(BusinessBaseException.class)
    protected ResponseEntity<ErrorResponse> handle(BusinessBaseException e) {
        logError(e.getMessage());
        return createErrorResponseEntity(e.errorCode);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handle(Exception e) {
        logError(e.getMessage());
        return createErrorResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private void logError(ErrorCode errorCode) {
        log.error("[ERROR] {}", errorCode.message);
    }

    private void logError(String message) {
        log.error("[ERROR] {}", message);
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.status)
                .body(ErrorResponse.of(errorCode));
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(ErrorCode errorCode, String customMessage) {
        return ResponseEntity
                .status(errorCode.status)
                .body(ErrorResponse.of(customMessage));
    }
}
