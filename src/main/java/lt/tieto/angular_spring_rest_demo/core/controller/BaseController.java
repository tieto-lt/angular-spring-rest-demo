package lt.tieto.angular_spring_rest_demo.core.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lt.tieto.angular_spring_rest_demo.core.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class BaseController {


    @ExceptionHandler(DataNotFoundException.class)
    private ResponseEntity<Void> handleResourceNotFoundException(DataNotFoundException e) {
        log.debug("Data not found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Void> exception(Exception e) {
        log.error("Internal error", e);
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldValidationError> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getFieldErrors()
                     .stream()
                     .map(error -> FieldValidationError
                             .builder()
                             .name(error.getField())
                             .message(error.getDefaultMessage())
                             .build())
                     .collect(Collectors.toList());
    }

    @Data
    @Builder
    static class FieldValidationError {
        private String name;
        private String message;
    }

}
