package by.loiko.recordbookbackend;

import by.loiko.recordbookbackend.exceptions.NonUniqueDataException;
import by.loiko.recordbookbackend.exceptions.ResourceNotFoundException;
import by.loiko.recordbookbackend.exceptions.TooMuchPrefferedWaysException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>("Запрашиваемый ресурс не найден", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonUniqueDataException.class)
    public ResponseEntity<String> handleNonUniqueDataException(NonUniqueDataException ex) {
        return new ResponseEntity<>("Не уникальные номера телефонов или адреса электронной почты",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TooMuchPrefferedWaysException.class)
    public ResponseEntity<String> handleTooMuchPrefferedWaysException(TooMuchPrefferedWaysException ex) {
        return new ResponseEntity<>("В категории может быть указан только один предпочтительный способ свзяи",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> errors.put(cv.getPropertyPath().toString(), cv.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
