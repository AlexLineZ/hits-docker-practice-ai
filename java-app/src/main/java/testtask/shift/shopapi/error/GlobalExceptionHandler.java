package testtask.shift.shopapi.error;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException ex, HttpServletRequest req) {
        ErrorResponse r = new ErrorResponse();
        r.status = HttpStatus.NOT_FOUND.value();
        r.error = HttpStatus.NOT_FOUND.getReasonPhrase();
        r.message = ex.getMessage();
        r.path = req.getRequestURI();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ErrorResponse r = new ErrorResponse();
        r.status = HttpStatus.BAD_REQUEST.value();
        r.error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        r.message = "Validation failed";
        r.path = req.getRequestURI();
        r.violations = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> {
                    if (err instanceof FieldError fe) {
                        return new ErrorResponse.FieldViolation(fe.getField(), fe.getDefaultMessage());
                    }
                    return new ErrorResponse.FieldViolation(err.getObjectName(), err.getDefaultMessage());
                })
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(r);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unknown(Exception ex, HttpServletRequest req) {
        ErrorResponse r = new ErrorResponse();
        r.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        r.error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        r.message = "Unexpected error";
        r.path = req.getRequestURI();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
    }
}
