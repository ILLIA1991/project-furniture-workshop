package project.furnitureworkshop.demo.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.furnitureworkshop.demo.exception.FurnitureWorkshopNotFoundException;
import project.furnitureworkshop.demo.exception.ValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class FurnitureWorkshopExceptionHandler {


    @ExceptionHandler({FurnitureWorkshopNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(FurnitureWorkshopNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }
}
